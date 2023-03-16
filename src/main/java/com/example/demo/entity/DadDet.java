package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * 직접광고 상세
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadDet {
    @Id @Column(name = "ada_det_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dadDetId; // 직접광고 상세 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad; // 광고 ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kwd_id")
    private Kwd kwd; // 키워드 ID (FK)
    @Column(name = "dad_cnr_status", nullable = false)
    private String dadCnrStatus; // 직접광고 검수 상태
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnr_req_id")
    private CnrReq cnrReq; // 검수 요청 ID (FK)
    @Column(name = "dad_use_config_yn", nullable = false)
    private Integer dadUseConfigYn; // 직접광고 사용 설정 여부
    @Column(name = "dad_act_yn", nullable = false)
    private Integer dadActYn; // 직접광고 활성 여부
    @Column(name = "reg_time", nullable = false)
    private ZonedDateTime regTime; // 등록시간
    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "dadDet", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private DadDetBid dadDetBid;

//    public void addCnrReq(CnrReq cnrReq) {
//        this.cnrReq = cnrReq;
//        cnrReq.setDadDet(this);
//    }

    public void update(CnrReq cnrReq) {
        DadDet.builder().cnrReq(cnrReq).build();
    }
}
