package fr.afpa.library.controller.admin;

import fr.afpa.library.model.Borrowing;
import fr.afpa.library.model.Copy;
import fr.afpa.library.model.Subscriber;
import fr.afpa.library.repository.SubscriberRepository;
import fr.afpa.library.service.BorrowingService;
import fr.afpa.library.service.CopyService;
import fr.afpa.library.service.SubscriberService;
import fr.afpa.library.exception.BorrowingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/manage")
public class ManageBorrowingsController {

    @Autowired
    public BorrowingService borrowingService;

    @Autowired
    public CopyService copyService;

    @Autowired
    public SubscriberService subscriberService;

    @Autowired
    public SubscriberRepository sr;

    @GetMapping("/borrowings")
    public ModelAndView books(){
        ModelAndView modelAndView = new ModelAndView("borrowings/createAndListBorrowings");
        modelAndView.addObject("borrowings", borrowingService.findAll());
        modelAndView.addObject("availableCopies", copyService.findByAvailableIsTrue());
        modelAndView.addObject("eligibleSubscribers", sr.findElligibleSubs());

        return modelAndView;
    }

    @PostMapping("/borrowing/new")
    public String createBorrowing(@RequestParam Map<String, String> params){
        Borrowing borrowing = new Borrowing();
        Copy copy = copyService.findOne(Long.valueOf(params.get("copy")));
        borrowing.setCopy(copy);
        borrowing.setSubscriber(subscriberService.findOne(Long.valueOf(params.get("sub"))));
        borrowingService.save(borrowing);

        return "redirect:/manage/borrowings";
    }

    @GetMapping("/borrowing/return/{id}")
    public String returnBorrowing(@PathVariable Long id) throws BorrowingNotFoundException, DataAccessException {
        Borrowing borrowing = borrowingService.findOne(id);
        borrowing.getCopy().setAvailable(true);

        LocalDate today = LocalDate.now();

        if(today.isAfter(borrowing.getExpectedReturnDate())){
            Subscriber subscriberToCheck = borrowing.getSubscriber();
            int subscriberDelays = subscriberToCheck.getTotalDelays()+1;
            subscriberToCheck.setTotalDelays(subscriberDelays);
            if(subscriberDelays > subscriberToCheck.getMembership().getMaxTotalDelays()){
                subscriberToCheck.setEnabled(false);
            }
        }

        borrowing.setReturnDate(LocalDate.now());
        borrowingService.save(borrowing);
        return "redirect:/manage/borrowings";
    }

    @GetMapping("/borrowing/delete/{id}")
    public String deleteBorrowing(@PathVariable Long id) throws BorrowingNotFoundException, DataAccessException {
        borrowingService.delete(id);
        return "redirect:/manage/borrowings";
    }
}
