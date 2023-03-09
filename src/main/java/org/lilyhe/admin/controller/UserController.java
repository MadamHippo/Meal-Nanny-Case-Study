package org.lilyhe.admin.controller;

import org.lilyhe.admin.model.Role;
import org.lilyhe.admin.model.User;
import org.lilyhe.admin.errors.UserNotFoundException;
import org.lilyhe.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Lily H.
 * handling user account management
 * provides CRUD operations for user accounts, allowing the
 * user w/ proper admins to manage accounts through web interface
 */

// Controller layer for request handling
@Controller
public class UserController {

    // injecting userService bean responsible for biz logic
    @Autowired
    private UserService service;

    // defining object (redirectAttribute) used to send messages between requests.
    private RedirectAttributes redirectAttributes;


    // Get request made to url /acc the list method is called which gets user accounts from UserService and sends it
    // to the account view page
    @GetMapping("/accounts")
    public String listAll(Model model){
        List<User> listAccounts = service.listAll();
        model.addAttribute("listAccounts", listAccounts);
        return "accounts";
    }

    // new account creation
    @GetMapping("/accounts/new")
    public String newUser(Model model){

        List<Role> listRoles = service.listRoles();

        User user = new User();
        // set active ticked by default
        user.setActive(true);

        //  This adds the user list roles, and page title object to the "model" object so that it can be accessed by
        //  the view.
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New Account");

        // this is the view (html) - its rendered by the controller method (newUser in UserController)
        return "account-reg";
    }

    // register New user account
    @GetMapping("/register")
    public String register(Model model){

        // Maybe I will consider disabled roles for user because this user is New.
        // List<Role> listRoles = service.listRoles();

        User user = new User();
        // set active ticked by default
        user.setActive(true);

        model.addAttribute("user", user);
        // Don't need this for registering because new users won't know their roles
        // model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New Account");

        return "account-reg";
    }

    // form submission for NEW users
    @PostMapping("/accounts/save")
    // takes 2 params - user and RA object that's for flash messages
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        System.out.println(user);

        service.save(user);
        redirectAttributes.addFlashAttribute("message", "Account saved successfully.");
        // redirect instruction to accounts page
        return "redirect:/accounts";
    }

    // edit and update function
    // read only method
    @GetMapping("/accounts/edit/{id}")
    public String editUser(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            User user = service.get(id);
            List<Role> listRoles = service.listRoles();

            model.addAttribute("listRoles", listRoles);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Editing Account ID: " + id);

            return "account-reg";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/accounts";
        }
    }

    // delete function for admin to remove users
    @GetMapping("/accounts/delete/{id}")
    public String deleteUser(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "User account " + id + " has been successfully removed!");

        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/accounts";
        }
        return "redirect:/accounts";

    }


}
