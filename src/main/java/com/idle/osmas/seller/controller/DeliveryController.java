package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.QnaDTO;
import com.idle.osmas.seller.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class DeliveryController {

    private final OrderListService orderListService;

    public DeliveryController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }


    @RequestMapping("/orderList")
    public String updateDeliveryStatus(@RequestParam(name = "checkList", required = false) List<Integer> checkList,
                                       @RequestParam(name ="exchangeList", required = false) List<Integer> exchangeList,
//                                       @RequestParam("randomList") List<Integer> randomList,
                                       Model model) {

        model.addAttribute("checkList", checkList);
        model.addAttribute("exchangeList", exchangeList);
//        model.addAttribute("randomList", randomList);
        System.out.println("exchangeList=====================" + exchangeList);

        Map<String, Object> params = new HashMap<>();
        params.put("checkList", checkList);
//        params.put("randomList", randomList);

        Map<String, Object> params2 = new HashMap<>();
        params2.put("exchangeList", exchangeList);

        if (checkList != null) {
            int result = orderListService.updateDeliveryStatus(params);
            int result2 = orderListService.updateShippingTrackInfo(params);

            if (result > 0 && result2 > 0) {
                return "redirect:/seller/orderList?listType=receipt";
            } else {
                return "redirect:/admin/errorPage";
            }
        }
        if (exchangeList != null) {

            int result3 = orderListService.exchangeDeliveryStatus(params2);

            if (result3 > 0) {
                return "redirect:/seller/orderList?listType=refund";
            } else {
                return "redirect:/admin/errorPage";
            }

        } else {
            return "redirect:/admin/errorPage";
        }
    }

}
