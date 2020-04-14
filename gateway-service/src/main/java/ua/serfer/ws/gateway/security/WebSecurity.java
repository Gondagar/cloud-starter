package ua.serfer.ws.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Value("${api.registration.url.path:/user-ws/users/registration}")
    String registration;// = environment.getProperty("api.registration.url.path");

    @Value("${api.login.url.path:/user-ws/users/login}")
    String login;// = environment.getProperty("api.login.url.path");

    @Value("${api.zuul.actuator.url.path}")
    String actuatorUrl;// = environment.getProperty("api.login.url.path");

    @Value("${api.users.actuator.url.path}")
    String userServiceActuatorUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();


        http.authorizeRequests()
                .antMatchers(actuatorUrl).permitAll()
                .antMatchers(userServiceActuatorUrl).permitAll()
                .antMatchers(HttpMethod.POST, registration).permitAll()
                .antMatchers(HttpMethod.POST, login).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(),environment));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }
}
