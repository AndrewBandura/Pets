package hw8.taxi.controller;

import hw8.taxi.domain.Client;
import hw8.taxi.exception.AuthenticationException;
import hw8.taxi.exception.ClientException;
import hw8.taxi.service.AuthorizationService;
import hw8.taxi.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Andrew on 18.10.2015.
 */
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    public static final Logger log = Logger.getLogger(AuthenticationController.class);

    @RequestMapping(value="/clients",method = RequestMethod.GET)
    public String showClients(Model model) {
        model.addAttribute("clientList", clientService.findAll());
        return "clients";
    }

    @RequestMapping(value="/registerClient",method = RequestMethod.GET)
    public String showRegisterClient() {
        return "registerClient";
    }

    @RequestMapping(value="/registerClient",method= RequestMethod.POST)
    public String executeRegister(@RequestParam("name") String name,
                                  @RequestParam("surname") String surname,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("address") String address,Model model)

    {
        try {
            if (clientService.createClient(name, surname, phone, address)) {
                model.addAttribute("message", "Registration completed successfully!");
            }

        } catch (ClientException e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "registerClient";
        }
        model.addAttribute("clientList", clientService.findAll());
         return "clients";
    }

    @RequestMapping(value = "/clientFilter", method = {RequestMethod.GET})
    public String showClientFilter() {
        return "clientFilter";
    }
    @RequestMapping(value = "/clientsFilteredByAmount", method = RequestMethod.POST)
    public String showClientsByAmount(@RequestParam("amountFrom") Integer amountFrom, Model model) {
        model.addAttribute("clientList", clientService.showClientsGtSum(amountFrom));
        return "clients";
    }

    @RequestMapping(value = "/clientsFilteredByOrders", method = RequestMethod.GET)
    public String showClientsByOrders(Model model) {
        model.addAttribute("clientList", clientService.showClientsLastMonth());
        return "clients";
    }
}
