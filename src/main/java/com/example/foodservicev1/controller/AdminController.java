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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@SessionAttributes({"saveResponse", "updateResponse", "deleteResponse", "loginResponse", "email"})
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    /////////////////////////// Homepage //////////////////////////////
    @GetMapping("/api/admin")
    public ModelAndView home(Model model) {
        model.addAttribute("saveResponse", -2);
        model.addAttribute("updateResponse", -2);
        model.addAttribute("deleteResponse", -2);
        if (model.getAttribute("email") == null) {
            return new ModelAndView("redirect:/api/admin/login");
        } /*else {
            String tmpemail = (String)model.getAttribute("email");
            if(tmpemail.equals("-1")) {
                return new ModelAndView("redirect:/api/admin/login");
            }
        }*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
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
        if ((int) model.getAttribute("saveResponse") == -2) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("saveResponse") == 0) {
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
        if ((int) model.getAttribute("updateResponse") == -2) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("updateResponse") == 0) {
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
        if ((int) model.getAttribute("saveResponse") != -1) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("saveResponse") == 0) {
            modalContent = "The email is existed";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "admin/save-customer";
    }

    @GetMapping("/api/admin/login")
    public String loginPage(Model model) {
        String modalContent = "Login successfully";
        String modalId = "modal";

        int response = -2;

        if (model.getAttribute("loginResponse") == null) {
            modalId = "notModal";
        } else {
            response = (int) model.getAttribute("loginResponse");
            if (response == -1) {
                modalContent = "The email does not exist!";
            } else if (response == 0) {
                modalContent = "Wrong password!";
            }
            model.addAttribute("modalId", modalId);
            model.addAttribute("modalContent", modalContent);
        }

        model.addAttribute("loginResponse", null);

        return "admin/login";
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
    public ModelAndView delete(Model model, @PathVariable String email, HttpSession httpsession, SessionStatus status) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("deleteResponse", adminService.delete(email));
        if (model.getAttribute("email").equals(email)) {
            /*Mark the current handler's session processing as complete, allowing for cleanup of
  session attributes.*/
            status.setComplete();
            /* Invalidates this session then unbinds any objects bound to it. */
            httpsession.invalidate();
            return new ModelAndView("redirect:/api/admin/login");
        }
        return new ModelAndView("redirect:/api/admin");
    }

    /////////////////////////// Login //////////////////////////////
    @PostMapping("api/admin/login")
    public ModelAndView login(Model model, @RequestParam String email, @RequestParam String password) {
        model.addAttribute("admin", new Admin());

        int response = adminService.login(email, password);
        model.addAttribute("loginResponse", response);

        if (response == 1) {
            model.addAttribute("email", email);
            return new ModelAndView("redirect:/api/admin");
        }
        return new ModelAndView("redirect:/api/admin/login");
    }

    /////////////////////////// Logout //////////////////////////////
    @GetMapping("/api/admin/logout")
    public ModelAndView logout(Model model, HttpSession httpsession, SessionStatus status) {
        /*Mark the current handler's session processing as complete, allowing for cleanup of
  session attributes.*/
        status.setComplete();
        /* Invalidates this session then unbinds any objects bound to it. */
        httpsession.invalidate();
        return new ModelAndView("redirect:/api/admin/login");
    }
}
