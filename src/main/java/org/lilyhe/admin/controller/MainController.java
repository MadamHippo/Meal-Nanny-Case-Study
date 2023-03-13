package org.lilyhe.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;



/**
 * @author Lily H.
 *
 * main controller handles incoming HTTP requests and returns appropriate responses
 */

// .
@Controller
public class MainController {

    // Handle http request of application and return the admin control panel:
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    // handles request of login/logout
    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    // handles request to community
    @GetMapping("/community")
    public String viewCommunity(Model model){
        model.addAttribute("cssFile", "map.css");
        return "community";
    }


}