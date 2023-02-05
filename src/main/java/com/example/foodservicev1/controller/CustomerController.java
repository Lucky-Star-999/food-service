package com.example.foodservicev1.controller;

import com.example.foodservicev1.entity.Food;
import com.example.foodservicev1.entity.Order;
import com.example.foodservicev1.entity.OrderFood;
import com.example.foodservicev1.service.FoodService;
import com.example.foodservicev1.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/customer/")
@SessionAttributes({"saveResponse", "updateResponse", "deleteResponse", "loginResponse", "email"})
@Controller
public class CustomerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    /////////////////////////// Homepage //////////////////////////////


    /////////////////////////// Read //////////////////////////////
    @GetMapping("restaurant/{username}")
    public String restaurantPage(Model model, @PathVariable String username) {
        model.addAttribute("restaurant", restaurantService.findByUsername(username));
        model.addAttribute("foods", foodService.findByRestaurantUsername(username));

        return "customer/restaurant";
    }




    /*@PostMapping("order")
    public String hello(@RequestParam List<String> idList, @RequestParam List<String> quantityList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));



        System.out.println(idList);
        System.out.println(quantityList);
        return "Hi";
    }*/


    /////////////////////////// Create //////////////////////////////
    @PostMapping("/order")
    public String restaurantPage(Model model,
                                 @RequestParam List<String> idList,
                                 @RequestParam List<String> nameList,
                                 @RequestParam List<Double> priceList,
                                 @RequestParam List<Integer> quantityList,
                                 @RequestParam String restaurantUsername) {




        LocalDateTime now = LocalDateTime.now();
        String uniqueID = UUID.randomUUID().toString();

        List<Food> foods = new ArrayList<>();
        List<OrderFood> orderFoods = new ArrayList<>();


        model.addAttribute("email", "theboost1305@gmail.com");

        Order order = new Order(uniqueID, restaurantUsername, (String)model.getAttribute("email"), now);




        for (int i=0; i<idList.size(); i++) {
            /*foods.add(new Food(idList.get(i), "luckrestaurant",
                    nameList.get(i), priceList.get(i), quantityList.get(i), ""));*/
            orderFoods.add(new OrderFood(order.getId(), idList.get(i), quantityList.get(i)));
        }

        String username = "luckrestaurant";
        model.addAttribute("restaurant", restaurantService.findByUsername(username));
        model.addAttribute("foods", foodService.findByRestaurantUsername(username));

        System.out.println(order);
        System.out.println(orderFoods);

        

        return "customer/restaurant";
    }


    /////////////////////////// Update //////////////////////////////


    /////////////////////////// Delete //////////////////////////////


    /////////////////////////// Login //////////////////////////////

}
