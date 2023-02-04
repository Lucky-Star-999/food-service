package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.Admin;
import com.example.foodservicev1.entity.Food;
import com.example.foodservicev1.entity.Restaurant;
import com.example.foodservicev1.service.FoodService;
import com.example.foodservicev1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("username", (String)model.getAttribute("username"));
        model.addAttribute("foods", foodService.findByRestaurantUsername((String)model.getAttribute("username")));
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




    /////////////////////////// Update //////////////////////////////
    @PutMapping("/api/restaurant/food")
    public ModelAndView update(Model model, @ModelAttribute Food food) {
        model.addAttribute("food", new Food());
        model.addAttribute("updateResponse", foodService.update(food));
        return new ModelAndView("redirect:/api/restaurant/update-food/" + food.getId());
    }



    /////////////////////////// Delete //////////////////////////////


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
}
