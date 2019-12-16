package com.hepta.mercado.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertTo(Object source, Class<T> target) {
        if (source == null)
            return null;
        try {
            return mapper.convertValue(source, target);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel realizar conversão de objeto");
        }
    }
}