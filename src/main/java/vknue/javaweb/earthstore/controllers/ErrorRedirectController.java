package vknue.javaweb.earthstore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorRedirectController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get the status code from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            if (statusCode == 404) {
                return "404"; // Return the name of the custom 404 error page
            }
        }
        return "/error"; // Fallback to a general error page
    }



}
