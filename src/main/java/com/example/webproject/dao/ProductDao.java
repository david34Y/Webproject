package com.example.webproject.dao;


import com.example.webproject.entity.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class ProductDao {

    public List<Product> listarProductos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, Product>> response = restTemplate.exchange(
                "https://dontulipan2-default-rtdb.firebaseio.com/Store/94gq7h9q94gq7h9q/Products.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Product>>() {});

        Map<String, Product> productsMap = response.getBody();
        if (productsMap != null) {
            return new ArrayList<>(productsMap.values());
        } else {
            return Collections.emptyList();
        }
    }
}
