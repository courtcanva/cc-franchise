package com.courtcanva.ccfranchise.models;

import lombok.*;

import javax.persistence.*;
// reference: https://www.baeldung.com/jpa-one-to-one
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "orderDetails")
    private Order order;

}
