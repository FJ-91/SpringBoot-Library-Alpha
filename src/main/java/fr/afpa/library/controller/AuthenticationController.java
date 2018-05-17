package fr.afpa.library.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login-processing")
    public String dispatchAfterLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toString().equals("[SUBSCRIBER]")){
            return "redirect:/subscriber/myborrowings";
        } else if(auth.getAuthorities().toString().equals("[ADMIN]")){
            return "redirect:/manage/borrowings";
        } else return "redirect:/error";
    }

}
