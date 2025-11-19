package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.LoanStatus;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> findByUser_Id(Long user_id) {
        return loanRepository.findByUser_Id(user_id);
    }

    public List<Loan> findByUser_IdAndStatus(Long userId, LoanStatus status) {
        return loanRepository.findByUser_IdAndStatus(userId, status);
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public List<Loan> findByUser(Long userId) {
        return loanRepository.findByUser_Id(userId);
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}