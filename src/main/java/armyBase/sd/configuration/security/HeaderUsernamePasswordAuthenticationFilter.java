package armyBase.sd.configuration.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class HeaderUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 
     */
    public HeaderUsernamePasswordAuthenticationFilter() {
        super();
        this.setFilterProcessesUrl("/**");
        this.setPostOnly(false);
    }

    
    @Override
    protected String obtainPassword(HttpServletRequest request) {
    	System.out.println("pass " + this.getPasswordParameter());
        return request.getHeader(this.getPasswordParameter());
    }

  
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getHeader(this.getPasswordParameter());
    } 
}