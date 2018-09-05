package armyBase.sd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



	/**
	 * @author 
	 */

	@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
	public class App{

	    /**
	     * Deploys the Spring application inside an embedded Tomcat
	     *
	     * @param args args
	     */
	    public static void main(String[] args) {
	    	//Login login = new Login();
	        //login.setVisible(true);
	        SpringApplication.run(App.class, args);
	    }
	}

