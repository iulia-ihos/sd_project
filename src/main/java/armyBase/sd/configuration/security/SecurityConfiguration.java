package armyBase.sd.configuration.security;



import armyBase.sd.dao.UserDAO;
import armyBase.sd.request.Encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserDAO.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
        	.antMatchers("/register").permitAll()
        	.antMatchers("/js/base/*").permitAll()
        	.antMatchers("/js/*").permitAll()
        	.antMatchers("/css/*").permitAll()
        	.antMatchers("/user/*").permitAll()
            .anyRequest().authenticated() 
            .and()
            .formLogin().permitAll();
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
            	System.out.println("encode : " + charSequence);
                return Encryption.encryptPassword(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	System.out.println("pass  " + charSequence.toString());//ui password
            	System.out.println("string " + s);//database password
            	return s.equals(encode(charSequence));
            }
        };
    }
}