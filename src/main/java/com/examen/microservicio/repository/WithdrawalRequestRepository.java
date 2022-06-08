package com.examen.microservicio.repository;

import com.examen.microservicio.model.WithdrawalRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WithdrawalRequestRepository extends MongoRepository<WithdrawalRequest, String> {
}