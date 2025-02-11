package com.purwadhika;

import com.purwadhika.model.Product;

import java.util.List;

public class ProductControlller {

    private List<Product> products;
    private long totalSale = 0;
    private double totalProductSold = 0;
    private Product mostBoughtProduct;
    private Product leastBoughtProduct;

    public ProductControlller(List<Product> products) {
        this.products = products;
    }

    public void analyst() {
        if(products == null) {
            throw new IllegalArgumentException("products is null");
        }

        products.stream().iterator().forEachRemaining(product -> {
            totalSale += product.getTotalSold();
            totalProductSold += (product.getTotalSold() * product.getItemPrice());
            if(mostBoughtProduct == null) {
                mostBoughtProduct = product;
            } else {
                if(product.getTotalSold() > mostBoughtProduct.getTotalSold()) {
                    mostBoughtProduct= product;
                }
            }
            if(leastBoughtProduct == null) {
                leastBoughtProduct = product;
            } else {
                if(product.getTotalSold() < leastBoughtProduct.getTotalSold()) {
                    leastBoughtProduct= product;
                }
            }

        });
    }

    public long getTotalSale() {
        return totalSale;
    }

    public double getTotalProductSold() {
        return totalProductSold;
    }

    public Product getMostBoughtProduct() {
        return mostBoughtProduct;
    }

    public Product getLeastBoughtProduct() {
        return leastBoughtProduct;
    }
}
