package com.kltn.promotion_service.component.promotion;

import com.kltn.promotion_service.component.coupon.Coupon;
import com.kltn.promotion_service.component.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotion")
public class Promotion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    private String description;

    private String imageUrl;

    private String discountType;

    private String startDate;

    private String endDate;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Coupon> couponList;
}
