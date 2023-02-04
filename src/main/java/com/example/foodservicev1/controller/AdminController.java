package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.Admin;
import com.example.foodservicev1.entity.Customer;
import com.example.foodservicev1.service.AdminService;
import com.example.foodservicev1.service.CustomerService;
import com.example.foodservicev1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes({"saveResponse","updateResponse", "deleteResponse"})
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/api/admin")
    public String home(Model model) {
        model.addAttribute("saveResponse", -2);
        model.addAttribute("updateResponse", -2);
        model.addAttribute("deleteResponse", -2);
        return "admin/home";
    }

    /////////////////////////// Read //////////////////////////////
    @GetMapping("/api/admin/admins")
    public String findAll(Model model) {
        model.addAttribute("admins", adminService.findAll());
        return "admin/find-admin";
    }

    @GetMapping("/api/admin/customers")
    public String findAllCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "admin/find-customer";
    }

    @GetMapping("/api/admin/restaurants")
    public String findAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "admin/find-restaurant";
    }

    @GetMapping("/api/admin/create-admin")
    public String saveAdminPage(Model model) {
        model.addAttribute("admin", new Admin());
        String modalId = "modal";
        String modalContent = "Create Admin successfully";
        if((int)model.getAttribute("saveResponse") == -2) {
            modalId = "notModal";
        } else if ((int)model.getAttribute("saveResponse") == 0){
            modalContent = "The email is existed";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "admin/save-admin";
    }

    @GetMapping("/api/admin/update-admin/{email}")
    public String updateAdminPage(Model model, @PathVariable String email) {
        model.addAttribute("admin", adminService.findByEmail(email));
        String modalId = "modal";
        String modalContent = "Update Admin successfully";
        if((int)model.getAttribute("updateResponse") == -2) {
            modalId = "notModal";
        } else if ((int)model.getAttribute("updateResponse") == 0){
            modalContent = "Something wrong happen!";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "admin/edit-admin";
    }

    @GetMapping("/api/admin/create-customer")
    public String saveCustomerPage(Model model) {
        model.addAttribute("customer", new Customer());
        String modalId = "modal";
        String modalContent = "Create Customer successfully";
        if((int)model.getAttribute("saveResponse") != -1) {
            modalId = "notModal";
        } else if ((int)model.getAttribute("saveResponse") == 0){
            modalContent = "The email is existed";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "admin/save-customer";
    }

    /////////////////////////// Create //////////////////////////////
    @PostMapping("/api/admin/admin")
    public ModelAndView save(Model model, @ModelAttribute Admin admin) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("saveResponse", adminService.save(admin));
        return new ModelAndView("redirect:/api/admin/create-admin");
    }

    @PostMapping("/api/admin/customer")
    public ModelAndView saveCustomer(Model model, @ModelAttribute Customer customer) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("saveResponse", customerService.save(customer));
        return new ModelAndView("redirect:/api/admin/create-customer");
    }

    /////////////////////////// Update //////////////////////////////
    @PutMapping("/api/admin/admin")
    public ModelAndView update(Model model, @ModelAttribute Admin admin) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("updateResponse", adminService.update(admin));
        return new ModelAndView("redirect:/api/admin/update-admin/" + admin.getEmail());
    }

    /////////////////////////// Delete //////////////////////////////
    @DeleteMapping("api/admin/admin/{email}")
    public ModelAndView delete(Model model, @PathVariable String email) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("deleteResponse", adminService.delete(email));
        return new ModelAndView("redirect:/api/admin");
    }
}
