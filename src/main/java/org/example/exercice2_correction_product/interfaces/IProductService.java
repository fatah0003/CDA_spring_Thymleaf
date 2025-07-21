package org.example.exercice2_correction_product.interfaces;

import org.example.exercice2_correction_product.model.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<Product> getProductList();
    Product getProductById(UUID id);
    List<Product> getProductFilteredByCategoryAndPrice(String category, Double price);
}
