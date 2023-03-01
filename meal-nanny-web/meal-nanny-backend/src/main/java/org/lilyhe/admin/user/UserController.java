package org.lilyhe.admin.user;

import org.lilyhe.common.entity.Role;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/accounts")
    public String listAll(Model model){
        List<User> listAccounts = service.listAll();
        model.addAttribute("listAccounts", listAccounts);
        return "accounts";
    }


    @GetMapping("/accounts/new")
    public String newUser(Model model){
        List<Role> listRoles = service.listRoles();

        User user = new User();
        // set active ticked by default
        user.setActive(true);


        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "account-reg";
    }

    // form submission
    @PostMapping("/accounts/save")
    public String saveUser(User user){
        System.out.println(user);
        service.save(user);
        return "redirect:/accounts";
    }


}
