package edu.whpu.controller;

import edu.whpu.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author bin_wen
 * @date 2025/2/7 00:18
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
    public List<Product> getList() {
        return Arrays.asList(new Product(10010L, "苹果手机13pro", 8999.99D));
    }
}
