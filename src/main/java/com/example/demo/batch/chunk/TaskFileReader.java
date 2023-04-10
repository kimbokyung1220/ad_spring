package com.example.demo.batch.chunk;

import com.example.demo.batch.dto.DadRptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

@RequiredArgsConstructor
public class TaskFileReader {
    public FlatFileItemReader<DadRptRequestDto> reader(String filePath) {
        // 요청상태가 REQ인 파일

        FlatFileItemReader<DadRptRequestDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(filePath));
        System.out.println(filePath);
        System.out.println("********************");
        reader.setEncoding("UTF-8"); // 인코딩
        reader.setLinesToSkip(1); // csv파일 header skip
        reader.setLineMapper(new DefaultLineMapper<DadRptRequestDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("basicDate", "dadDetId", "impCnt", "clickCnt", "avgImpRank", "avgCpc", "adSpend");
                setDelimiter(",");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<DadRptRequestDto>() {{
                setTargetType(DadRptRequestDto.class);
            }});
        }});

        return reader;
    }
}
