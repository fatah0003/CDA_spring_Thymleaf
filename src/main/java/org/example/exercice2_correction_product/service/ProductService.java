package org.example.exercice2_correction_product.service;

import org.example.exercice2_correction_product.interfaces.IProductService;
import org.example.exercice2_correction_product.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final Map<UUID, Product> productList;

    public ProductService() {
        productList = new HashMap<>();

        Product product1 = Product.builder()
                .category("Electronique")
                .id(UUID.randomUUID())
                .name("PS5")
                .price(540)
                .build();
        Product product2 = Product.builder()
                .category("Electronique")
                .id(UUID.randomUUID())
                .name("PS5 Lite")
                .price(540)
                .build();
        Product product3 = Product.builder()
                .category("Electronique")
                .id(UUID.randomUUID())
                .name("XBOX")
                .price(200)
                .build();
        Product product4 = Product.builder()
                .category("Electronique")
                .id(UUID.randomUUID())
                .name("Switch")
                .price(350)
                .build();
        Product product5 = Product.builder()
                .category("Mode")
                .id(UUID.randomUUID())
                .name("t-shirt")
                .price(40)
                .build();
        Product product6 = Product.builder()
                .category("Mode")
                .id(UUID.randomUUID())
                .name("chaussette")
                .price(20)
                .build();

        productList.put(product1.getId(),product1);
        productList.put(product2.getId(),product2);
        productList.put(product3.getId(),product3);
        productList.put(product4.getId(),product4);
        productList.put(product5.getId(),product5);
        productList.put(product6.getId(),product6);
    }

    @Override
    public List<Product> getProductList() {
        return productList.values().stream().toList();
    }

    @Override
    public Product getProductById(UUID id) {
        //return productList.get(id);
        return productList.values().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> getProductFilteredByCategoryAndPrice(String category, Double price) {

        if(category == null && (price == null || price <= 0)) {
            return new ArrayList<>();
        }

        List<Product> allProducts = getProductList();
        List<Product> filteredProducts = new ArrayList<>();

        boolean matched;
        for(Product p : allProducts) {
            matched = true;
            if(category != null && !p.getCategory().equals(category)) {
                matched = false;
            }

            if(price != null && p.getPrice() > price) {
                matched = false;
            }

            if(matched)
                filteredProducts.add(p);
        }
        return filteredProducts;

//        return getProductList().stream()
    //        .filter(p -> category == null || p.getCategory().equals(category))
    //        .filter(p -> price == null || price <= 0 || p.getPrice() <= price)
    //        .collect(Collectors.toList());;
    }
}
