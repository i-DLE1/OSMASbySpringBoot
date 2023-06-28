package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/restrict")
public class restrictController {

    @GetMapping("restrict_receive")
    public void restrict_receive(){}

    @GetMapping("restrict_sanction")
    public void restrict_sanction(){}

    @GetMapping("restrict_view")
    public void restrict_view(){}
}
