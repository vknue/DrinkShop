package vknue.javaweb.earthstore.controllers;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableWebSecurity
@RequestMapping("/checkOut")
public class CheckOutController {

    @GetMapping("/pay")
    public String showCart(Model model) {
        return "checkOut";
    }
}
