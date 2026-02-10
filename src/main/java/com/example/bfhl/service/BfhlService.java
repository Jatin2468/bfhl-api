package com.example.bfhl.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

    @Service
    public class BfhlService{

        // ==============================
        // Fibonacci
        // ==============================
        public List<Integer> fibonacci(int n) {

            List<Integer> result = new ArrayList<>();

            int first = 0;
            int second = 1;

            for (int i = 0; i < n; i++) {
                result.add(first);

                int next = first + second;
                first = second;
                second = next;
            }

            return result;
        }


        // ==============================
        // Prime numbers from array
        // ==============================
        public List<Integer> primes(List<Integer> numbers) {

            List<Integer> result = new ArrayList<>();

            for (int num : numbers) {
                if (isPrime(num)) {
                    result.add(num);
                }
            }

            return result;
        }

        private boolean isPrime(int n) {

            if (n <= 1) return false;

            for (int i = 2; i < n; i++) {
                if (n % i == 0)
                    return false;
            }

            return true;
        }


        // ==============================
        // LCM
        // ==============================
        public int lcm(List<Integer> numbers) {

            int lcm = numbers.get(0);

            for (int i = 1; i < numbers.size(); i++) {
                lcm = (lcm * numbers.get(i)) / gcd(lcm, numbers.get(i));
            }

            return lcm;
        }


        // ==============================
        // HCF (GCD)
        // ==============================
        public int hcf(List<Integer> numbers) {

            int hcf = numbers.get(0);

            for (int i = 1; i < numbers.size(); i++) {
                hcf = gcd(hcf, numbers.get(i));
            }

            return hcf;
        }


        // ==============================
        // GCD helper
        // ==============================
        private int gcd(int a, int b) {

            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }

            return a;
        }
    }