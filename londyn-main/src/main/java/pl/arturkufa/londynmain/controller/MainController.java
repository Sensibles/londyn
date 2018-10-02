package pl.arturkufa.londynmain.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class MainController {

    @GetMapping("/hello")
    public ModelAndView testEndpoint(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("username", currentPrincipalName);
        return modelAndView;
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
        if (Objects.nonNull(auth)) {
            new SecurityContextLogoutHandler()
                    .logout(request, response, auth);
            request.getSession().invalidate();
        }
        return "logout";
    }
}
