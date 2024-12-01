package com.kltn.product_service.component.brand;

import com.kltn.product_service.component.entity.BaseEntity;
import com.kltn.product_service.component.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "information", nullable = false)
    private String information;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

}
