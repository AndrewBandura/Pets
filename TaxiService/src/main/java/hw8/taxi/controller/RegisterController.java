package hw8.taxi.controller;

import hw8.taxi.domain.Role;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.AuthorizationException;
import hw8.taxi.service.AuthenticationService;
import hw8.taxi.service.AuthorizationService;
import hw8.taxi.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrew on 18.10.2015.
 */
@Controller
public class RegisterController {
    @Autowired
    private AuthorizationService authorizationService;
    public static final Logger log = Logger.getLogger(AuthenticationController.class);

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String showRegister() {
        return "register";
    }


    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String executeRegister(@RequestParam("name") String name,
                                  @RequestParam("password") String password,
                                  @RequestParam("passwordConfirm") String passwordConfirm,
                                  @RequestParam("personalId") String personalId,
                                  @RequestParam("role") String role,
                                  Model model)

    {
        try {
            if (authorizationService.registerWithPasswordConfirm(name, personalId, password, passwordConfirm,new Role(role))) {
                model.addAttribute("message", "Registration completed successfully!");
            }

        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "register";
        }
         return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public String showDashboard() {
        return "dashboard";
    }

}
