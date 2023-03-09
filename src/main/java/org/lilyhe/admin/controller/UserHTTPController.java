package org.lilyhe.admin.controller;

import org.lilyhe.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Lily H.
 * basic RESTful webservice implementation usi..
 */

@RestController
public class UserHTTPController {
    @Autowired
    private UserService service;

    // maps HTTP POST requests to the /accounts/check_email endpoint to the checkAccountDupe method
    @PostMapping("/accounts/check_email")
    // public method that takes 2 params id and email, bound to http request
    public String checkAccountDupe(@Param("id") Integer id, @Param("email") String email) {
        // check if email unique or not
        return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
