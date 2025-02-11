package com.purwadhika;

import com.purwadhika.model.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Practice07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculate calculate = new Calculate();
        while(true) {
            System.out.print("Input: ");
            String number = scanner.nextLine();
            if(number == null) {
                System.out.println("Invalid input.");
            } else {
                if(number.trim().equals("")) {
                    System.out.println("Invalid input.");
                } else {
                    if(number.equalsIgnoreCase("q")) {
                        break;
                    }
                    try{
                        calculate.addNumber(calculate.readNumber(number));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input.");
                    }
                }
            }
            System.out.println("Please enter valid number or 'q' to finish.");
        }
        try {
            System.out.println("Output: " + calculate.calculateAverage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();

        List<Callable<Product>> readLineCallables = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("product_sales_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("Product Name")) {
                    continue;
                }
                ReadLineCallable readLineCallable = new ReadLineCallable(line);
                readLineCallables.add(readLineCallable);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Product> products = new ArrayList<>();
        try {
            List<Future<Product>> futures = executor.invokeAll(readLineCallables);

            for (Future<Product> future : futures) {
                try {
                    Product product = future.get(); // Memproses hasil
                    if (product != null) {
                        products.add(product);
                    } else {
                        System.out.println("Data produk tidak valid.");
                    }
                } catch (ExecutionException e) {
                    System.out.println("Kesalahan dalam tugas Callable: " + e.getCause().getMessage());
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eksekusi terganggu: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        ProductControlller productControlller = new ProductControlller(products);
        productControlller.analyst();
        System.out.println("Total Sales: " + productControlller.getTotalSale());
        System.out.println("Total Product Sold: " + productControlller.getTotalProductSold());
        System.out.println("Most Bought Product: " + productControlller.getMostBoughtProduct().getProductName());
        System.out.println("Least Bought Product: " + productControlller.getLeastBoughtProduct().getProductName());
    }
}
