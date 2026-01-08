package com.dogukantez.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sold_car",
uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"},
name = "uq_gallerist_car_customer")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoldCar extends BaseEntity{
    @ManyToOne
    private Gallerist gallerist;

    @OneToOne
    private Car car;

    @ManyToOne
    private Customer customer;


}
