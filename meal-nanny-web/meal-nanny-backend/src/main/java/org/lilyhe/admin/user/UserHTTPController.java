package org.lilyhe.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// basic RESTful webservice implementation using...
@RestController
public class UserHTTPController {
    @Autowired
    private UserService service;

    @PostMapping("/accounts/check_email")
    public String checkAccountDupe(@Param("email") String email) {
        return service.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
