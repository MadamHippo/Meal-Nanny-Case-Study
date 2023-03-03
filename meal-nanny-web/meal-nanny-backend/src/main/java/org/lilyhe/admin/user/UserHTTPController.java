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
    public String checkAccountDupe(@Param("id") Integer id, @Param("email") String email) {
        return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
