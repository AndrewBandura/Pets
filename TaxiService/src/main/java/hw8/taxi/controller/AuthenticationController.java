package hw8.taxi.controller;
import hw8.taxi.domain.User;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.InvalidPasswordException;
import hw8.taxi.exception.PasswordExpiredException;
import hw8.taxi.exception.UserBlockedException;
import hw8.taxi.service.AuthenticationService;
import hw8.taxi.service.OrderService;
import hw8.taxi.service.UserService;
import hw8.taxi.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 15.10.2015.
 */

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Autowired
    Properties properties;

    public static final Logger log = Logger.getLogger(AuthenticationController.class);
    private Map<String,Integer> loginAttemptsControl=new HashMap<>();

    @RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.HEAD})
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView("index");
        User user = new User();
        model.addObject("user", user);
        log.info(user);

        return model;
    }

    @RequestMapping(value="/index",method={RequestMethod.GET,RequestMethod.HEAD})
    public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        session.setAttribute("idUser",0L);
        session.setAttribute("nameUser","logged off");
        ModelAndView model = new ModelAndView("index");
        User user = new User();
        model.addObject("user", user);
        log.info(user);

        return model;
    }

    @RequestMapping(value="/index",method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session, @ModelAttribute("user")User user)

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
                session.setAttribute("idUser",userService.getUserByName(user.getName()).getId());
                session.setAttribute("nameUser",user.getName());
                if(authenticationService.getRole(user.getName()).getName().equals("driver")){
                    model = new ModelAndView("drvDashboard");
                }
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
        log.info("Go to: "+model.getViewName());
        return model;
    }

    @RequestMapping(value = "/drvdashboard", method = {RequestMethod.GET})
    public String showDrvDashboard() {
        return "drvDashboard";
    }


    @RequestMapping(value = "/getAllOrdersAndroid", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody
    List loginAndGetOrdersAndroid(@RequestParam("login") String login,
                       @RequestParam("password") String password,
                       HttpServletRequest request, HttpServletResponse response,HttpSession session) {

        try
        {
            System.out.println("/get all android controller,login:"+login+" ,password:"+password);
            boolean isUserValid = authenticationService.authenticate(login, password);
            if(isUserValid)
            {
                System.out.println("User login is successful");
                request.setAttribute("loggedInUser", login);
                request.setAttribute("message","Welcome, "+login);
                session.setAttribute("idUser",userService.getUserByName(login).getId());
                session.setAttribute("nameUser",login);
                List list = orderService.findAll();
                return list;
            }
        }
        catch (InvalidPasswordException e){
            String message = "Invalid password!";
            Integer loginAttemptsCommited = loginAttemptsControl.get(login);
            if(loginAttemptsCommited==null)loginAttemptsCommited=0;
            loginAttemptsControl.put(login,++loginAttemptsCommited);

            if(loginAttemptsCommited==properties.getLoginAttemptsLimit()){
                message = "Login attempts limit exceeded! Your account is blocked.";
                authenticationService.block(login, password,true);
            }
//            model = new ModelAndView("index");
//            model.addObject("user", user);
            request.setAttribute("error", message);
            e.printStackTrace();
        }

        catch(PasswordExpiredException e){
//            model = new ModelAndView("index");
//            model.addObject("user", user);
            request.setAttribute("error", "Your password is expired!");
            e.printStackTrace();

        }

        catch(UserBlockedException e){
//            model = new ModelAndView("index");
//            model.addObject("user", user);
            request.setAttribute("error", "Your account is blocked!");
            e.printStackTrace();

        }

        catch(AuthenticationException e)
        {
//            model = new ModelAndView("index");
//            model.addObject("user", user);
            request.setAttribute("error", "No such user!");
            e.printStackTrace();
        }

        catch(Exception e)
        {
//            model = new ModelAndView("index");
//            model.addObject("user", user);
            request.setAttribute("error", "Unknown error!");
            e.printStackTrace();
        }
        //log.info("Go to: "+model.getViewName());
        return null;
    }

}



