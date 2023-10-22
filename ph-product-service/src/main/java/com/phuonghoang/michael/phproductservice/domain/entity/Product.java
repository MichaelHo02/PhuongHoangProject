package com.phuonghoang.michael.phproductservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @Column(name = "code", updatable = false, nullable = false)
    private Long code;
    private String name;
    private String description;

    @ElementCollection(targetClass = String.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category", nullable = false)
    private Set<String> categories = new HashSet<>();
}
