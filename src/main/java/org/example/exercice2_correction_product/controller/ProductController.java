package org.example.exercice2_correction_product.controller;

import org.example.exercice2_correction_product.interfaces.IProductService;
import org.example.exercice2_correction_product.model.Product;
import org.example.exercice2_correction_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProductList();
        model.addAttribute("products", products);
        return "acceuil";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable UUID id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "detail";
    }

    @GetMapping("/filter")
    public List<Product> filteredProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double price,
            Model model
    ) {
        return productService.getProductFilteredByCategoryAndPrice(category, price);
    }

}
