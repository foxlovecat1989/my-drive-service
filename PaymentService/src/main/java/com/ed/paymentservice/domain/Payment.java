package com.ed.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Payment {
    @Id
    @SequenceGenerator(
            name = "payment_id_sequence",
            sequenceName = "payment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_id_sequence"
    )
    private Long seqNo;
    private UUID paymentId;
    private UUID clientId;
    private UUID driverId;
    private Integer distance;
    private Float rate;
    private String coupon;
    private Float discount;
    private PayMethod payMethod;
    private PaymentState paymentState;
    private LocalDateTime transactionTime;
    @CreationTimestamp
    private LocalDateTime createAt;
}
