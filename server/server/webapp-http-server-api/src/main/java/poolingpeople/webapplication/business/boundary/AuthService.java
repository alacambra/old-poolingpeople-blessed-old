package poolingpeople.webapplication.business.boundary;

import javax.xml.bind.DatatypeConverter;

public class AuthService {

    public void loadCredentials(String authRawHeader, ILoggedUserContainer loggedUserContainer) {
    	
        if(authRawHeader == null || authRawHeader.length() <= 0 
        		|| !authRawHeader.subSequence(0, "Basic ".length()).equals("Basic ")) return;
        authRawHeader = authRawHeader.substring("Basic ".length());
        
        byte[] auth_binary = DatatypeConverter.parseBase64Binary(authRawHeader);

        if (auth_binary == null || auth_binary.length < 1) {
            return;
        }
        
        String[] decodedAuth = new String(auth_binary).split(":", 2);
        loggedUserContainer.setEmail(decodedAuth[0]);
        loggedUserContainer.setPassword(decodedAuth[1]);
        
//        loggedUserContainer.setEmail("albert.lacambra@ion2s.com");
//        loggedUserContainer.setPassword("a");
        
    }

}
