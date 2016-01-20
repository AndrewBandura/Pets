package hw8.taxi.controller;

import hw8.taxi.domain.Order;
import hw8.taxi.domain.User;
import hw8.taxi.service.OrderService;
import hw8.taxi.service.UserService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
@Controller
public class DrvOrderController {
    public static final Logger log = Logger.getLogger(AuthenticationController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/drvOrders", method = {RequestMethod.GET})
    public String showDrvOrders(Model model) {
        model.addAttribute("orderList", orderService.showOrdersByPortion());
        return "drvOrders";
        //return "auth";
    }

    //returns List of arrays {id,name,phone,addressFrom,addressTo,amount}
    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    public @ResponseBody List getAll(HttpSession session) {
        Long idDriver = (Long)session.getAttribute("idUser");
        List list = orderService.findAllWeb(idDriver);
        return list;
    }

    //returns List of arrays {id,name,phone,addressFrom,addressTo,amount}
    @RequestMapping(value = "/getAllOrdersAndroid_", method = RequestMethod.GET)
    public @ResponseBody List getAllOrdersAndroid() {
        List list = orderService.findAll();
        return list;

//        JSONArray ja = new JSONArray();
//        for(Object elem:list) {
//            int index=0;
//            for (Object el : (Object[]) elem) {
//                JSONObject jo = new JSONObject();
//                try {
//                    jo.put(String.valueOf(index), el);
//                    ja.put(jo);
//                } catch (JSONException e) {
//                }
//                index++;
//            }
//        }
//
//        return ja;
    }

    @RequestMapping(value = "/getHeader", method = RequestMethod.GET)
    public @ResponseBody String getHeader(HttpSession session) {
        String nameDriver= (String)session.getAttribute("nameUser");
        return "user: "+nameDriver;

    }
}
