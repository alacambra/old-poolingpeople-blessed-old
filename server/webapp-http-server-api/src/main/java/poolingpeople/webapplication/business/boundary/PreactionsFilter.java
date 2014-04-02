package poolingpeople.webapplication.business.boundary;

import java.io.IOException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.jboss.weld.Weld;

import poolingpeople.commons.cdihelper.CDIInstanceProvider;
import poolingpeople.commons.helper.Pager;
import poolingpeople.commons.helper.PagerImpl;

public class PreactionsFilter implements Filter{

	//	@Inject
	//	ILoggedUserContainer loggedUserContainer;

	@Inject
	AuthService authService;

	@Inject 
	Instance<ILoggedUserContainer> iLoggedUserContainerSource;

	@Inject
	Instance<Pager> pagerSource;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest r = (HttpServletRequest) request;
		ILoggedUserContainer loggedUserContainer = iLoggedUserContainerSource.get();

		String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
		getPagerParams(r.getQueryString());

		authService.loadCredentials(authHeader, loggedUserContainer);
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
	}

	/*
	 * Just experimental code!!!
	 */
	private void getPagerParams(String query){

		if(query == null || "".equals(query)){
			return;
		}

		Pager pager = pagerSource.get();
		try{
			String[] params = query.split("&");  
			for (String param : params)  
			{  

				String name = param.split("=")[0];
				String value = param.split("=")[1];

				if("start".equals(name)){
					pager.setStart(Integer.parseInt(value));
				}else if("size".equals(name)){
					pager.setSize(Integer.parseInt(value));
				}
			}
		} catch (Exception e){
			pager.setSize(15);
			pager.setStart(0);
		}

	}

}
