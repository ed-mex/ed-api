package com.vincent.edmex.controller;

import com.vincent.edmex.pojo.User;
import com.vincent.edmex.service.RoomService;
import com.vincent.edmex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @GetMapping("/register")
    public String showForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Integer> roomIds = roomService.getAllRoomId();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roomIds", roomIds);

        return "userManager/register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        boolean insertResult = userService.addNewUser(user);

        model.addAttribute("insertResult", insertResult);

        return "userManager/register_result";

    }

    @GetMapping("/user-manager")
    public String showUserManagerForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userManager/user_manager";

    }

    @GetMapping("/user-modify")
    public String showUserModifyForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("users", user);
        return "userManager/user_modify";

    }

    //TODO: errore 500 con inserimento senza selezionare ruolo
    @PostMapping("/user-modify")
    public String updateUserModifyForm(User user, Model model) {
        model.addAttribute("users", user);
        if (!userService.userAlreadyExists(user.getUsername())) {
            model.addAttribute("error", "User not Exists");
        }
        return "userManager/user_modify";

    }

}
