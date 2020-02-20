package com.galvanize.gmdbusers.util;

import org.modelmapper.ModelMapper;

public class Mapper {
    public static <T extends Object> T mapAll(Object input, Class<T> output) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(input, output);
    }
}
