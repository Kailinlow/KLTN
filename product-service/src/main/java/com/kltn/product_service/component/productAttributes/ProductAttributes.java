package com.kltn.product_service.component.productAttributes;

import com.kltn.product_service.component.brand.Brand;
import com.kltn.product_service.component.entity.BaseEntity;
import com.kltn.product_service.component.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "productAttributes")
public class ProductAttributes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "attribute_name", nullable = false)
    private String attributeName;

    @Column(name = "attribute_value", nullable = false)
    private String attributeValue;

    @Column(name = "order_point", nullable = false)
    private Integer oderPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
