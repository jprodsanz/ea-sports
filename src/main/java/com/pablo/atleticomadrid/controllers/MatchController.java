package com.pablo.atleticomadrid.controllers;

import com.pablo.atleticomadrid.models.Match;
import com.pablo.atleticomadrid.models.User;
import com.pablo.atleticomadrid.services.MatchService;
import com.pablo.atleticomadrid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MatchController {
    @Autowired
    private UserService userService;
    @Autowired
    private MatchService service;

    @GetMapping("/match/create")
    public String CreateMatch(HttpSession session, Model model, RedirectAttributes redirect) {
        if (session.getAttribute("userId") == null) {
            redirect.addFlashAttribute("error", "You must login to access content");
            return "redirect:/user/login";
        }
        model.addAttribute("users", userService.getAll());
        model.addAttribute("match", new Match());
        return "match-create.jsp";
    }

    @GetMapping("/match/{match_id}/view")
    public String ViewMatch(HttpSession session,
                            @PathVariable("match_id") Long match_id,
                            Model model, RedirectAttributes redirect) {
        //check if user is logged in
        Long id = (Long) session.getAttribute("userId");
        if (id == null){
            redirect.addFlashAttribute("error","You must login" );
            return "redirect:/user/login";
        }
        // get the specific match
        // load the match onto the page
        User loggedUser = userService.getOne(id);
        model.addAttribute("user", loggedUser);
        Match oneMatch = service.getOne(match_id);
        model.addAttribute("oneMatch", oneMatch);
        // return the page
        return "match-view.jsp";
    }

    @PostMapping("/match/create")
    public String CreateMatch(
            @Valid @ModelAttribute("match") Match match, BindingResult result, Model model, HttpSession session
    ) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }
        // Link the matches on the dashboard to their own pages
        // display a list of the user's matches and a list of the match the user is not a part of
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAll());
            return "match-create.jsp";
        }
//        match.getPlayers().add(userService.getOne((Long) session.getAttribute("userId")));
        match.setSubmittedBy(userService.getOne((Long) session.getAttribute("userId")));
        service.createOrUpdate(match);
        return "redirect:/";
    }

    @GetMapping("/match/{match_id}/update")
    public String EditMatch(
            @PathVariable("match_id") Long match_id, Model model, HttpSession session, RedirectAttributes redirect) {
            //
            Long id = (Long) session.getAttribute("userId");
            Match oneMatch = service.getOne(match_id);

            if (id !=oneMatch.getSubmittedBy().getId()) {
                session.setAttribute("userId", null);
                redirect.addFlashAttribute("error", "Please login to access content");
                return "redirect:/user/login";
            }
            User loggedUser = userService.getOne(id);
            model.addAttribute("user", loggedUser);
            model.addAttribute("updateMatch", oneMatch);
            return "match-edit.jsp";
    }
    @PutMapping("/match/{match_id}/update")
    public String UpdateMatch(
            @Valid @ModelAttribute("updateMatch") Match updateMatch, BindingResult result,
            @PathVariable("match_id") Long match_id, HttpSession session) {
            //
            Long id = (Long) session.getAttribute("userId");
            Match oneMatch = service.getOne(match_id);

            if (id !=oneMatch.getSubmittedBy().getId()) {
                session.setAttribute("userId", null);
                return "redirect:/";
            }
            if (result.hasErrors()){
                return "match-edit.jsp";
            }
            updateMatch.setId(match_id);
            service.createOrUpdate(updateMatch);
            return "redirect:/";
    }

    @GetMapping("/match/{match_id}/delete")
    public String DeleteMatch(
            @PathVariable("match_id") Long match_id, HttpSession session) {
            //
            Long id = (Long) session.getAttribute("userId");

            Match oneMatch = service.getOne(match_id);
            if (id !=oneMatch.getSubmittedBy().getId()) {
            session.setAttribute("userId", null);
            return "redirect:/user/login";
        }
            service.deleteOne(match_id);
            return "redirect:/";

    }

}
