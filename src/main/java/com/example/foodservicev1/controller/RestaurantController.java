package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.Admin;
import com.example.foodservicev1.entity.Customer;
import com.example.foodservicev1.entity.Food;
import com.example.foodservicev1.entity.Restaurant;
import com.example.foodservicev1.service.FoodService;
import com.example.foodservicev1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@SessionAttributes({"saveResponse", "updateResponse", "deleteResponse", "loginResponse", "username"})
@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    /////////////////////////// Homepage //////////////////////////////
    @GetMapping("/api/restaurant")
    public ModelAndView home(Model model) {
        model.addAttribute("saveResponse", -2);
        model.addAttribute("updateResponse", -2);
        model.addAttribute("deleteResponse", -2);
        if (model.getAttribute("username") == null) {
            return new ModelAndView("redirect:/api/restaurant/login");
        }
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("username", (String) model.getAttribute("username"));
        model.addAttribute("foods", foodService.findByRestaurantUsername((String) model.getAttribute("username")));
        modelAndView.setViewName("restaurant/home");
        return modelAndView;
    }


    /////////////////////////// Read //////////////////////////////
    @GetMapping("/api/restaurant/login")
    public String loginPage(Model model) {
        String modalContent = "Login successfully";
        String modalId = "modal";

        int response = -2;

        if (model.getAttribute("loginResponse") == null) {
            modalId = "notModal";
        } else {
            response = (int) model.getAttribute("loginResponse");
            if (response == -1) {
                modalContent = "The username does not exist!";
            } else if (response == 0) {
                modalContent = "Wrong password!";
            }
            model.addAttribute("modalId", modalId);
            model.addAttribute("modalContent", modalContent);
        }

        model.addAttribute("loginResponse", null);

        return "restaurant/login";
    }

    // Page of Create Restaurant (Register)
    @GetMapping("/api/restaurant/register")
    public String saveRestaurantPage(Model model) {
        // Add object for form
        model.addAttribute("restaurant", new Restaurant());

        // Set up the modal message
        String modalId = "modal";
        String modalContent = "Create Restaurant successfully";
        if (model.getAttribute("saveResponse") == null) {
            modalId = "notModal";
        } else {
            if ((int) model.getAttribute("saveResponse") == -2) {
                modalId = "notModal";
            } else if ((int) model.getAttribute("saveResponse") == 0) {
                modalContent = "The username is existed";
            }
        }

        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);

        // Return the page
        return "restaurant/save-restaurant";
    }

    // Page of Update Restaurant (Profile)
    @GetMapping("/api/restaurant/profile")
    public String updateRestaurantPage(Model model) {
        model.addAttribute("restaurant", restaurantService.findByUsername((String) model.getAttribute("username")));
        String modalId = "modal";
        String modalContent = "Update Restaurant successfully";
        if ((int) model.getAttribute("updateResponse") == -2) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("updateResponse") == 0) {
            modalContent = "Something wrong happen!";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "restaurant/profile";
    }

    @GetMapping("/api/restaurant/create-food")
    public String createFoodPage(Model model) {
        model.addAttribute("food", new Food());
        String modalId = "modal";
        String modalContent = "Update Food successfully";
        if ((int) model.getAttribute("saveResponse") == -2) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("saveResponse") == 0) {
            modalContent = "Something wrong happen!";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "restaurant/create-food";
    }

    @GetMapping("/api/restaurant/update-food/{id}")
    public String updateFoodPage(Model model, @PathVariable String id) {
        model.addAttribute("food", foodService.findById(id));
        String modalId = "modal";
        String modalContent = "Update Food successfully";
        if ((int) model.getAttribute("updateResponse") == -2) {
            modalId = "notModal";
        } else if ((int) model.getAttribute("updateResponse") == 0) {
            modalContent = "Something wrong happen!";
        }
        model.addAttribute("modalId", modalId);
        model.addAttribute("modalContent", modalContent);
        return "restaurant/edit-food";
    }


    /////////////////////////// Create //////////////////////////////
    @PostMapping("/api/restaurant/restaurant")
    public ModelAndView saveRestaurant(Model model, @ModelAttribute Restaurant restaurant) {
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("saveResponse", restaurantService.save(restaurant));
        return new ModelAndView("redirect:/api/restaurant/register");
    }

    @PostMapping("/api/restaurant/food")
    public ModelAndView saveFood(Model model, @ModelAttribute Food food) {
        model.addAttribute("food", new Food());
        food.setRestaurantUsername((String)model.getAttribute("username"));
        model.addAttribute("saveResponse", foodService.save(food));
        return new ModelAndView("redirect:/api/restaurant/create-food");
    }


    /////////////////////////// Update //////////////////////////////
    @PutMapping("/api/restaurant/restaurant")
    public ModelAndView updateCustomer(Model model, @ModelAttribute Restaurant restaurant) {
        model.addAttribute("customer", new Restaurant());
        model.addAttribute("updateResponse", restaurantService.update(restaurant));
        return new ModelAndView("redirect:/api/restaurant/profile");
    }

    @PutMapping("/api/restaurant/food")
    public ModelAndView update(Model model, @ModelAttribute Food food) {
        model.addAttribute("food", new Food());
        model.addAttribute("updateResponse", foodService.update(food));
        return new ModelAndView("redirect:/api/restaurant/update-food/" + food.getId());
    }


    /////////////////////////// Delete //////////////////////////////
    @DeleteMapping("api/restaurant/restaurant/{username}")
    public ModelAndView delete(Model model, @PathVariable String username,
                               HttpSession httpsession, SessionStatus status) {
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("deleteResponse", restaurantService.delete(username));
        status.setComplete();
        httpsession.invalidate();
        return new ModelAndView("redirect:/api/restaurant/login");
    }


    /////////////////////////// Login //////////////////////////////
    @PostMapping("api/restaurant/login")
    public ModelAndView login(Model model, @RequestParam String username, @RequestParam String password) {
        model.addAttribute("restaurant", new Restaurant());

        int response = restaurantService.login(username, password);
        model.addAttribute("loginResponse", response);

        if (response == 1) {
            model.addAttribute("username", username);
            return new ModelAndView("redirect:/api/restaurant");
        }
        return new ModelAndView("redirect:/api/restaurant/login");
    }

    /////////////////////////// Logout //////////////////////////////
    @GetMapping("/api/restaurant/logout")
    public ModelAndView logout(Model model, HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return new ModelAndView("redirect:/api/restaurant/login");
    }
}
