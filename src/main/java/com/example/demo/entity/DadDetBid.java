package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 직접광고 상세 입찰
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadDetBid implements Serializable {

    public DadDetBid(DadDet dadDet) {
        this.dadDet = dadDet;
    }

    @Id
    @Column(name = "dad_det_id")
    private Long dadDetId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dad_det_id", referencedColumnName = "dad_det_id")
    private DadDet dadDet;

    @Column(name = "bid_cost")
    private Integer bidCost; // 입찰 금액

    public DadDetBid addCost(Long dadDetId, Integer bidCost) {
        return DadDetBid.builder()
                .dadDetId(dadDetId)
                .dadDet(dadDet)
                .bidCost(bidCost)
                .build();


    }
}
