package com.example.qrpayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonIO {
    static public <T> T JsonString_to_Object(String JSON_string,Class<T> objectClass ) throws JsonProcessingException {
        System.out.println(objectClass.getClass());
        T newObject = new ObjectMapper().readValue(JSON_string,objectClass);
        return newObject;
    }
    static public String Object_to_JsonString(Object object) throws JsonProcessingException {
        String newJsonString = new ObjectMapper().writeValueAsString(object);
        return newJsonString;
    }

}
