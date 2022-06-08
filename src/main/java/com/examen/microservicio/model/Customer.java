package com.examen.microservicio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("customer")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
    private String afp;
    private Double availableAmount;
    private Date withdrawalDate;
    private String numberAccount;
}
