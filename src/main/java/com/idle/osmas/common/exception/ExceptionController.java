package com.idle.osmas.common.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccessAuthorityException.class)
    public String AccessAuthorityException(AccessAuthorityException e, Model model){
        model.addAttribute("error",e.getMessage());
        return "errors/accessAuthority";
    }

    @ExceptionHandler(ClosedProjectException.class)
    public String ClosedProjectException(ClosedProjectException e, Model model){
        model.addAttribute("error", e.getMessage());
        return "errors/closedProject";
    }

}
