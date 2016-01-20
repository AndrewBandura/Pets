package session18.controller;
import hw8.taxi.domain.User;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.InvalidPasswordException;
import hw8.taxi.exception.PasswordExpiredException;
import hw8.taxi.exception.UserBlockedException;
import hw8.taxi.service.AuthenticationService;
import hw8.taxi.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 15.10.2015.
 */

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    Properties properties;

    public static final Logger log = Logger.getLogger(AuthenticationController.class);
    private Map<String,Integer> loginAttemptsControl=new HashMap<>();

    @RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.HEAD})
    public String displayLogin(HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView("index");
        User user = new User();
        model.addObject("user", user);
        log.info(user);

        return "index";
    }

    @RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.HEAD})
    public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView("index");
        User user = new User();
        model.addObject("user", user);
        log.info(user);

        return model;
    }

    @RequestMapping(value="/index",method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user")User user)

    {
        ModelAndView model= null;
        try
        {
            boolean isUserValid = authenticationService.authenticate(user.getName(), user.getPassword());
            if(isUserValid)
            {
                System.out.println("User login is successful");
                request.setAttribute("loggedInUser", user.getName());
                request.setAttribute("message","Welcome, "+user.getName());
                model = new ModelAndView("dashboard");
            }
        }
        catch (InvalidPasswordException e){
            String message = "Invalid password!";
            Integer loginAttemptsCommited = loginAttemptsControl.get(user.getName());
            if(loginAttemptsCommited==null)loginAttemptsCommited=0;
            loginAttemptsControl.put(user.getName(),++loginAttemptsCommited);

            if(loginAttemptsCommited==properties.getLoginAttemptsLimit()){
                message = "Login attempts limit exceeded! Your account is blocked.";
                authenticationService.block(user.getName(), user.getPassword(),true);
            }
            model = new ModelAndView("index");
            model.addObject("user", user);
            request.setAttribute("error", message);
            e.printStackTrace();
        }

        catch(PasswordExpiredException e){
            model = new ModelAndView("index");
            model.addObject("user", user);
            request.setAttribute("error", "Your password is expired!");
            e.printStackTrace();

        }

        catch(UserBlockedException e){
            model = new ModelAndView("index");
            model.addObject("user", user);
            request.setAttribute("error", "Your account is blocked!");
            e.printStackTrace();

        }

        catch(AuthenticationException e)
        {
            model = new ModelAndView("index");
            model.addObject("user", user);
            request.setAttribute("error", "No such user!");
            e.printStackTrace();
        }

        catch(Exception e)
        {
            model = new ModelAndView("index");
            model.addObject("user", user);
            request.setAttribute("error", "Unknown error!");
            e.printStackTrace();
        }
        return model;
    }

}



