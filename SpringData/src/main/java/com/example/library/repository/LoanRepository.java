package com.example.library.repository;

import com.example.library.model.Loan;
import com.example.library.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUser_Id(Long userId);

    List<Loan> findByUser_IdAndStatus(Long userId, LoanStatus status);
}
