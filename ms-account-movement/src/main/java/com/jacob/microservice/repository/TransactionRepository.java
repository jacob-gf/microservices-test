package com.jacob.microservice.repository;

import com.jacob.microservice.entity.AccountEntity;
import com.jacob.microservice.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByAccountAndDateBetween(AccountEntity account, LocalDateTime startDate, LocalDateTime endDate);

}
