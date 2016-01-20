package hw8.taxi.controller;

import hw8.taxi.domain.Client;
import hw8.taxi.domain.User;
import hw8.taxi.service.ClientService;
import hw8.taxi.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Andrew on 19.10.2015.
 */
@Controller
public class OrderServlet {
    public static final Logger log = Logger.getLogger(AuthenticationController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/orders", method = {RequestMethod.GET})
    public String showOrders(Model model, HttpSession session) {
        Long idUser = (Long)session.getAttribute("idUser");
        if(idUser==0L){
//            ModelAndView modView = new ModelAndView("index");
//            User user = new User();
            return null;
        }


        model.addAttribute("orderList", orderService.showOrdersByPortion());
        return "orders";
    }

    @RequestMapping(value = "/order", method = {RequestMethod.GET})
    public String showCreateOrder(Model model) {
        List<Client> clientList = clientService.findAll();
        model.addAttribute("clientList", clientList);
        return "order";
    }

    @RequestMapping(value = "/editOrder", method = {RequestMethod.POST})
    public String showEditOrder() {
        return "order";
    }

    @RequestMapping(value = "/orderFilter", method = {RequestMethod.GET})
    public String showOrderFilter() {
        return "orderFilter";
    }

    @RequestMapping(value = "/ordersFiltered", method = RequestMethod.POST)
    public String showOrders(@RequestParam("amountFrom") Long amountFrom,
                             @RequestParam("amountTo") Long amountTo,
                             Model model) {
        model.addAttribute("orderList", orderService.showOrders(amountFrom, amountTo));
        return "orders";
    }

    @RequestMapping(value = "/createEditOrder", method = RequestMethod.POST)
    public String createEditOrder(@RequestParam("orderId") Long orderId,
                                  @RequestParam("clientId") Long clientId,
                                  @RequestParam("addressFrom") String addressFrom ,
                                  @RequestParam("addressTo") String addressTo ,
                                  @RequestParam("amount") String amount,
                                  Model model) {

        try {
            Client client = clientService.read(clientId);
            if(!orderService.createOrder(orderId,client,amount,addressFrom,addressTo)) {
                model.addAttribute("error", "Failed to register an order!");
            }
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("message", "Registration success!");
        model.addAttribute("orderList", orderService.showOrdersByPortion());
        return "orders";

    }
}
