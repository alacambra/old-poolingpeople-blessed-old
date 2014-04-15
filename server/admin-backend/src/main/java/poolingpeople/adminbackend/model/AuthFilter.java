package poolingpeople.adminbackend.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private HttpSession httpSession;
	private FilterChain filterChain;

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			gatherHttpRequestResponseObjects(request, response, filterChain);
			processRequest();
		} catch (Throwable t) {
			System.err.println(t);
		}
	}

	private void processRequest() throws IOException, ServletException {
		String requestURI = httpRequest.getRequestURI();
		if (isAccessingIndex(requestURI) || hasSession(httpSession)
				|| isAccessingPublicFolder(requestURI)
				|| isAccessingFacesResources(requestURI)) {
			filterChain.doFilter(httpRequest, httpResponse);
		} else {
			redirectToIndex(httpRequest, httpResponse);
		}
	}

	private void gatherHttpRequestResponseObjects(ServletRequest request,
			ServletResponse response, FilterChain filterChain) {
		httpRequest = (HttpServletRequest) request;
		httpResponse = (HttpServletResponse) response;
		httpSession = httpRequest.getSession(false);
		this.filterChain = filterChain;
	}

	private void redirectToIndex(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException {
		httpResponse
				.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
	}

	private boolean isAccessingFacesResources(String reqURI) {
		return reqURI.contains("javax.faces.resource");
	}

	private boolean isAccessingPublicFolder(String reqURI) {
		return reqURI.indexOf("/public/") >= 0;
	}

	private boolean hasSession(HttpSession httpSession) {
		return httpSession != null
				&& httpSession.getAttribute("username") != null;
	}

	private boolean isAccessingIndex(String reqURI) {
		return reqURI.indexOf("/login.xhtml") >= 0;
	}

	@Override
	public void destroy() {
	}
}