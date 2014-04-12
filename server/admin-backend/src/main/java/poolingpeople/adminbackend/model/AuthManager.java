package poolingpeople.adminbackend.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class AuthManager implements Serializable {
	private static final long serialVersionUID = 6100698340283697186L;
	private String password;
	
	    private String message, uname;
		private boolean loggedIn;
	 
	    public String getMessage() {
	        return message;
	    }
	 
	    public void setMessage(String message) {
	        this.message = message;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	 
	    public String getUname() {
	        return uname;
	    }
	 
	    public void setUname(String uname) {
	        this.uname = uname;
	    }
	 
	    public String loginProject() {
	        boolean result = (uname.equals("admin") && password.equals("1234"));
	        if (result) {
	            // get Http Session and store username
	            HttpSession session = Util.getSession();
	            session.setAttribute("username", uname);
	            loggedIn = true;
	            return "index.xhtml";
	        } else {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "Invalid Login!",
	                    "Please Try Again!"));
	 
	            // invalidate session, and redirect to other pages
	 
	            //message = "Invalid Login. Please Try Again!";
	            return "login.xhtml";
	        }
	    }
	 
	    public boolean isLoggedIn() {
			return loggedIn;
		}

		public String logout() {
	      HttpSession session = Util.getSession();
	      session.invalidate();
	      return "login.xhtml";
	   }
}
