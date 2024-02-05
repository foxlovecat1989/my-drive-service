package com.ed.paymentservice.transaction.model.entity;

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
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_id_sequence",
            sequenceName = "transaction_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_sequence"
    )
    private Long seqNo;
    private UUID transactionId;
    private UUID fromId;
    private UUID toId;
    private Integer amount;
    @CreationTimestamp
    private LocalDateTime createAt;
}
