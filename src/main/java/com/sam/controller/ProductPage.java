package com.sam.controller;

import com.sam.pojo.ProductEntity;
import com.sam.pojo.UserEntity;
import com.sam.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductPage {
    @RequestMapping(value="/showAll",method= RequestMethod.GET)
    public ModelAndView listProducts(HttpSession httpSession, HttpServletRequest request){
        UserEntity userinfo = (UserEntity) httpSession.getAttribute("USER_SESSION");
        if (userinfo != null) {
            request.setAttribute("username", userinfo.getUsername());
            request.setAttribute("email", userinfo.getEmail());
            request.setAttribute("formlogin", "none".toString());
            request.setAttribute("formsetting", "inline".toString());

        } else {
            request.setAttribute("username", "null");
            request.setAttribute("email", "null");
            request.setAttribute("formlogin", "inline");
            request.setAttribute("formsetting", "none");
        }
        ModelAndView mv = new ModelAndView("views/product");
        mv.addAllObjects(ProductService.prepareRenderData());
        return mv;
    }

    @RequestMapping(value="/searchDatabase",method= RequestMethod.POST)
    @ResponseBody
    public List<ProductEntity> searchProducts(@RequestParam("firstLevelFilter") String firstLevelFilter,
                                              @RequestParam("secondLevelFilter") String secondLevelFilter){
        return ProductService.filterProducts(firstLevelFilter, secondLevelFilter);
    }
}