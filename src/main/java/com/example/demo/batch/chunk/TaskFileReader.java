package com.example.demo.batch.chunk;

import com.example.demo.batch.FlatFileParseExceptionHandler;
import com.example.demo.batch.dto.DadRptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.step.skip.NonSkippableReadException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class TaskFileReader {
    public FlatFileItemReader<DadRptRequestDto> reader(String filePath) {
        FlatFileItemReader<DadRptRequestDto> reader = new FlatFileItemReader<>();
        // 요청상태가 REQ인 파일
        String fileName = filePath.substring(filePath.indexOf("-")+1);
        try{
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
        } catch (NonSkippableReadException e) {
            e.getMessage();
            return null;
        }

        return reader;
    }
}
