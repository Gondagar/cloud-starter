package ua.serfer.ws.account.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/status/check")
    public String status(HttpServletRequest request) {

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()){

            String key = parameterNames.nextElement();
            String parameter = request.getParameter(key);
            System.out.println(key + " = " + parameter);

        }

        return "Working";
    }

}
