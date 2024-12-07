package com.kltn.product_service.component.review;

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
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer rating;

    private String comment;

    private String commentStatus;

    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
