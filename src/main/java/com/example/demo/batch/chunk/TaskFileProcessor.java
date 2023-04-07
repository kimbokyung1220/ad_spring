package com.example.demo.batch.chunk;

import com.example.demo.batch.dto.DadRptRequestDto;
import com.example.demo.entity.DadRpt;
import com.example.demo.repository.DadDetDslRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TaskFileProcessor implements ItemProcessor<DadRptRequestDto, DadRpt> {
    private final DadDetDslRepositoryImpl dadDetRepository;
    private Map<String, DadRptRequestDto> processedDataMap = new HashMap<>(); // 중복된 I
    // D의 합계를 저장할 맵

    @Override
    public DadRpt process(DadRptRequestDto item) throws Exception {
        String advId = dadDetRepository.getAdvId(item.getDadDetId());


        // 비교할 키값
        String key = item.getBasicDate() + item.getDadDetId();

        if (processedDataMap.containsKey(key)) { // 중복된 데이터인 경우
            DadRptRequestDto existingData = processedDataMap.get(key);
            double avgImpRankRound = ((existingData.getAvgImpRank() + item.getAvgImpRank()) / 2);

            existingData.setBasicDate(item.getBasicDate());
            existingData.setDadDetId(item.getDadDetId());
            existingData.setImpCnt(existingData.getImpCnt() + item.getImpCnt());
            existingData.setClickCnt(existingData.getClickCnt() + item.getClickCnt());
            existingData.setAvgImpRank(Double.valueOf(String.format("%.1f", avgImpRankRound)));
            existingData.setAvgCpc((existingData.getAvgCpc() + item.getAvgCpc()) / 2);
            existingData.setAdSpend(existingData.getAdSpend() + item.getAdSpend());
            processedDataMap.put(key, existingData);

            return existingData.of(advId);
        } else {
            processedDataMap.put(key, item);
            return item.of(advId);
        }
    }
}
