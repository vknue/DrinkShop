package vknue.javaweb.earthstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vknue.javaweb.earthstore.models.Category;
import vknue.javaweb.earthstore.models.UserLog;
import vknue.javaweb.earthstore.repositories.ICategoryRepository;
import vknue.javaweb.earthstore.repositories.IUserLogRepository;
import vknue.javaweb.earthstore.services.UserLogService;

import java.util.List;

@Controller
@EnableWebSecurity
@RequestMapping("/log")
public class LogController {

    @Autowired
    private UserLogService userLogService;

    public LogController() {

    }


    @GetMapping("/index")
    public String index(Model model) {
        List<UserLog> logs = userLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "log";
    }
}