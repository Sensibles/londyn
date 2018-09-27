package pl.arturkufa.londynmain.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String testEndpoint(){
        return "Hello, I'm Londyn main!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler()
                    .logout(request, response, auth);
            request.getSession().invalidate();
        }
        return "logout";
    }
}
