package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
