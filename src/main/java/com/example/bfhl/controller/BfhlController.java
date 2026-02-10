package com.example.bfhl.controller;


import com.example.bfhl.dto.ApiResponse;
import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    private final BfhlService service;

    @Value("${official.email}")
    private String email;

    public BfhlController(BfhlService service){
        this.service = service;
    }

    // =========================
    // GET /health
    // =========================
    @GetMapping("/health")
    public ResponseEntity<ApiResponse> health(){
        return ResponseEntity.ok(
                new ApiResponse(true, email, null)
        );
    }

    // =========================
    // POST /bfhl
    // =========================
    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse> bfhl(@RequestBody BfhlRequest req){

        Object result = null;

        try{
            if(req.fibonacci != null)
                result = service.fibonacci(req.fibonacci);

            else if(req.prime != null)
                result = service.primes(req.prime);

            else if(req.lcm != null)
                result = service.lcm(req.lcm);

            else if(req.hcf != null)
                result = service.hcf(req.hcf);

            else if(req.AI != null)
                result = simpleAI(req.AI);

            else
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false,email,"Invalid input"));

            return ResponseEntity.ok(
                    new ApiResponse(true,email,result)
            );

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false,email,"Server error"));
        }
    }

    // simple AI (for now)
    private String simpleAI(String question){
        if(question.toLowerCase().contains("maharashtra"))
            return "Mumbai";
        return "Unknown";
    }
}