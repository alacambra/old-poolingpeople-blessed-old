package poolingpeople.adminbackend.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class AuthManager implements Serializable {
	private static final long serialVersionUID = 6100698340283697186L;
	private String password;
	private String userName;
	private boolean loggedIn;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String login() {
		if (isLoginValid()) {
			HttpSession session = Util.getSession();
			session.setAttribute("username", userName);
			loggedIn = true;
			return "index.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Invalid Login!", "Please Try Again!"));
			return "login.xhtml";
		}
	}

	private boolean isLoginValid() {
		return userName.equals("admin") && password.equals("1234");
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login.xhtml";
	}

	static class Util {
		public static HttpSession getSession() {
			return (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
		}

		public static HttpServletRequest getRequest() {
			return (HttpServletRequest) FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
		}

		public static String getUserName() {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			return session.getAttribute("username").toString();
		}

		public static String getUserId() {
			HttpSession session = getSession();
			if (session != null)
				return (String) session.getAttribute("userid");
			else
				return null;
		}
	}
}
