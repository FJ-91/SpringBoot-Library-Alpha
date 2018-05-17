package fr.afpa.library.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({BorrowingNotFoundException.class, NumberFormatException.class})
    public ModelAndView handleBorrowingNotFoundException(){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errortitle", "Borrowing doesn't exist !");

        return mav;
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataAccessException(){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errortitle", "Database error.");

        return mav;
    }

}
