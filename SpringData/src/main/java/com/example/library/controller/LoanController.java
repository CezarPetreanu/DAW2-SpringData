package com.example.library.controller;


import com.example.library.model.Loan;
import com.example.library.model.User;
import com.example.library.service.LoanService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {


    private final LoanService loanService;


    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @PostMapping
    public Loan create(@RequestBody Loan loan) {
        return loanService.save(loan);
    }


    @GetMapping("/user/{userId}")
    public List<Loan> findByUser(@PathVariable Long userId) {
        return loanService.findByUser_Id(userId);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}