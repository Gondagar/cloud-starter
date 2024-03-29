package ua.serfer.ws.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {


    Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorization = request.getHeader(environment.getProperty("authorization.token.header.name"));
        if (authorization == null || !authorization.startsWith(environment.getProperty("authorization.token.header.prefix"))){
            chain.doFilter(request, response);
            return;
         }

        UsernamePasswordAuthenticationToken authentication =  getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authorization = request.getHeader(environment.getProperty("authorization.token.header.name"));
        if(authorization == null) {
            return null;

        }
        String token =   authorization.replace(environment.getProperty("authorization.token.header.prefix"),"");
        Claims body = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody();
        String userId = body.getSubject();

        System.out.println("Decoded token = " + body);
        if(userId == null){
            return  null;
        }

        return new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());

    }


}
