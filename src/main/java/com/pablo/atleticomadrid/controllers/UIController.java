package com.pablo.atleticomadrid.controllers;

import com.pablo.atleticomadrid.models.LoginUser;
import com.pablo.atleticomadrid.models.Match;
import com.pablo.atleticomadrid.models.User;
import com.pablo.atleticomadrid.services.MatchService;
import com.pablo.atleticomadrid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UIController {
    @Autowired
    private UserService userService;
    @Autowired
    private MatchService matchService;

    // Dashboard once user is logged in
    @GetMapping("/")
    public String Dashboard(Model model, HttpSession session, RedirectAttributes redirect) {
        if(session.getAttribute("userId") == null) {
            redirect.addFlashAttribute("error", "You must login to access content");
            return "redirect:/user/login";
        }
        User u = userService.getOne((Long) session.getAttribute("userId"));
        model.addAttribute("user", u);
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("allMatches", matchService.getAll());
        return "index.jsp";
    }
    // Register render route
    @GetMapping("/user/register")
    public String UserRegister(@ModelAttribute("user") User user) {
        return "register.jsp";
    }

    // Login render route
    @GetMapping("/user/login")
    public String UserLogin(@ModelAttribute("loginUser") LoginUser loginUser) {
        return "login.jsp";
    }


}
