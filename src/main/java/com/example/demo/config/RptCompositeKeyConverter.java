package com.example.demo.config;

import com.example.demo.entity.RptCompositeKey;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RptCompositeKeyConverter implements AttributeConverter<RptCompositeKey, String> {

    private static final String SEPARATOR = "|";

    @Override
    public String convertToDatabaseColumn(RptCompositeKey rptCompositeKey) {
        if (rptCompositeKey == null) {
            return null;
        }
        return rptCompositeKey.getAdvId() + SEPARATOR + rptCompositeKey.getBasicDate() + SEPARATOR + rptCompositeKey.getDadDetId();
    }

    @Override
    public RptCompositeKey convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        String[] parts = value.split("\\" + SEPARATOR);
        return new RptCompositeKey(parts[0], parts[1], Long.parseLong(parts[2]));
    }
}