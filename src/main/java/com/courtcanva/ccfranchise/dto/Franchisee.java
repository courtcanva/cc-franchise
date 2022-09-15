package com.courtcanva.ccfranchise.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "franchisee")
public class Franchisee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;
    private String address;
}
