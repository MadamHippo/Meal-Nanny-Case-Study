package org.lilyhe.mainsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Handle http request of application and return the main site:
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

}