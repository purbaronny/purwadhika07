package com.purwadhika;

import com.purwadhika.model.Product;

import java.util.concurrent.Callable;

public class ReadLineCallable implements Callable<Product> {

    private String text;

    public ReadLineCallable(String text) {
        this.text = text;
    }

    @Override
    public Product call() throws Exception {
        if(text == null) {
            return null;
        }

        if(text.trim().isEmpty()) {
            return null;
        }

        String[] words = text.split(",");
        if(words == null) {
            return  null;
        }

        if(words.length != 3) {
            return null;
        }

        Product product = new Product();
        product.setProductName(words[0]);

        if(words[1] == null || words[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Total Sold is null or empty string");
        }

        int totalSold = 0;
        try {
            totalSold = Integer.valueOf(words[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Total Sold is not integer value");
        }

        if(totalSold < 0) {
            throw new IllegalArgumentException("Total Sold must be positive value");
        }
        product.setTotalSold(totalSold);

        if(words[2] == null || words[2].trim().isEmpty()) {
            throw new IllegalArgumentException("Total Sold is null or empty string");
        }

        float itemPrice = 0;
        try {
            itemPrice = Float.valueOf(words[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Item Price is not float value");
        }
        if(itemPrice < 0) {
            throw new IllegalArgumentException("Item Price must be positive value");
        }
        product.setItemPrice(itemPrice);

        return product;
    }
}
