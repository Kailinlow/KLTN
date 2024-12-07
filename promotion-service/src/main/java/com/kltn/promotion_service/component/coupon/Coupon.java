package com.kltn.promotion_service.component.coupon;

import com.kltn.promotion_service.component.entity.BaseEntity;
import com.kltn.promotion_service.component.promotion.Promotion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupon")
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    private BigDecimal discount;

    private String expirationDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CouponStatus status = CouponStatus.VALID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;
}
