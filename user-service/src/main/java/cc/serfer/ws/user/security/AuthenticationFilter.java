package cc.serfer.ws.user.security;

import cc.serfer.ws.user.service.UserService;
import cc.serfer.ws.user.transfer.LoginRequestModel;
import cc.serfer.ws.user.transfer.LoginResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    private Environment environment;

    private AuthenticationManager authenticationManager;

    ObjectMapper objectMapper;

    public AuthenticationFilter(UserService userService, Environment environment, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);

        objectMapper = new ObjectMapper();

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            LoginRequestModel creds = objectMapper.readValue(request.getInputStream(), LoginRequestModel.class);
            System.out.println(creds);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("User not found");
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {

        UserDetailsIml userDetailsIml = ((UserDetailsIml) auth.getPrincipal());
        String username = userDetailsIml.getUsername();
        String userUUIDId = userDetailsIml.getUserUUIDId();

        String token = Jwts.builder()
                .setSubject(userUUIDId)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();

        LoginResponseModel build = LoginResponseModel.builder()
                .token(token)
                .userId(userUUIDId).build();

        String body = objectMapper.writeValueAsString(build);

        response.addHeader("token",token);
        response.addHeader("userId",userUUIDId);
        response.addHeader("Content-Type", "application/json");
        response.getOutputStream().write(body.getBytes());
        response.getOutputStream().flush();


    }
}
