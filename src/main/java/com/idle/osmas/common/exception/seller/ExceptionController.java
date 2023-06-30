package com.idle.osmas.common.exception.seller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {

    @ExceptionHandler(ProjectRetryException.class)
    public String projectRetryException(ProjectRetryException exception, Model model){
        model.addAttribute("error", exception);
        return "seller/error/projectRetry";
    }

}
