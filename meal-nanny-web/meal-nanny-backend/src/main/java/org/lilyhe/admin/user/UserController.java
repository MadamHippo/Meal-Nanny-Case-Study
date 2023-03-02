package org.lilyhe.admin.user;

import org.lilyhe.common.entity.Role;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// Controller layer for request handling

@Controller
public class UserController {

    @Autowired
    private UserService service;
    private RedirectAttributes redirectAttributes;

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


        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create New Account");

        return "account-reg";
    }

    // form submission
    @PostMapping("/accounts/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        System.out.println(user);
        service.save(user);
        redirectAttributes.addFlashAttribute("message", "Account saved successfully.");
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

    // delete function
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
