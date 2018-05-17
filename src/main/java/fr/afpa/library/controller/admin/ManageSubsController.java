package fr.afpa.library.controller.admin;

import fr.afpa.library.model.Subscriber;
import fr.afpa.library.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/manage")
public class ManageSubsController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/subscribers/all")
    public ModelAndView allSubs(){
        ModelAndView modelAndView = new ModelAndView("admin/allSubscribers");
        modelAndView.addObject("subscribers", subscriberService.findAll());
        return modelAndView;
    }

    @GetMapping("/subscriber/new")
    public ModelAndView newSubForm(){
        ModelAndView modelAndView = new ModelAndView("admin/newSubscriber");
        modelAndView.addObject("subscriber", new Subscriber());
        return modelAndView;
    }

    @PostMapping("/subscriber/new")
    public String newSubPost(@Valid Subscriber subscriber, BindingResult bindingResult){

        if(subscriberService.findByEmail(subscriber.getEmail()) != null){
            bindingResult.rejectValue("email", "email.errors", subscriber.getEmail()+" is already taken");
        }

        if (bindingResult.hasErrors()){
            return "admin/newSubscriber";
        } else {
            subscriberService.save(subscriber);
            return "redirect:/manage/subscribers/all";
        }
    }

    @GetMapping("/subscriber/delete/{id}")
    public String deleteSub(@PathVariable Long id, RedirectAttributes r){

        if(subscriberService.exists(id)){
            String name = subscriberService.findOne(id).getFirstname()+" "+subscriberService.findOne(id).getLastname();
            r.addFlashAttribute("deleteSuccess", "Subscriber "+name+" successfully deleted");
            subscriberService.delete(id);
        }
        return "redirect:/manage/subscribers/all";
    }

}
