package com.itcast.ssm.controller;

import com.itcast.ssm.service.IProductService;
import com.itcst.ssm.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        System.out.println("表现层执行了。。");
        List<Product> products = productService.findAll();
        for (Product product:products) {
            System.out.println(product.getDepartureTime());
            System.out.println(product.getDepartureTimeStr());
            System.out.println(product.getProductStatus());
            System.out.println(product.getProductStatusStr());
        }
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }
}
