package com.purwadhika;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    private List<Integer> numbers;

    public Calculate() {
        numbers = new ArrayList<>();
    }

    public void addNumber(int number) {
        numbers.add(number);
    }

    public int readNumber(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("value is null or empty string");
        }

        try{
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("value is not integer value");
        }
    }

    public float calculateAverage() {
        if(numbers.isEmpty()) {
            throw new IllegalArgumentException("No number in the list");
        }
        long total = 0;
        for(int number : numbers) {
            total += number;
        }
        return total / numbers.size();
    }
}
