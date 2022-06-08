package com.examen.microservicio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("withdrawal")
@Data
@NoArgsConstructor
public class WithdrawalRequest {
    @Id
    private String requestId;
    private String withdrawalAmount;
    private String afp;
    private String customerId;
}
