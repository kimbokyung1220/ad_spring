package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class RptCompositeKey implements Serializable {
    @Column(name = "adv_id")
    private String advId; // 광고주 ID
    @Column(name = "basic_date")
    private String basicDate; // 기준날짜
    @Column(name = "dad_det_id")
    private Long dadDetId; // 광고주 ID

    public RptCompositeKey(String advId, String basicDate, Long dadDetId) {
        this.advId = advId;
        this.basicDate = basicDate;
        this.dadDetId = dadDetId;
    }
}
