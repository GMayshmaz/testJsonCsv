package com.goeuro.client;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

@Consumes("application/json")
@Provider
public class GoEuroListBodyReader implements MessageBodyReader<List> {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == List.class;
    }

    @Override
    public List readFrom(Class<List> listClass,
                         Type type, Annotation[] annotations,
                         MediaType mediaType,
                         MultivaluedMap<String, String> stringStringMultivaluedMap,
                         InputStream inputStream)
            throws IOException, WebApplicationException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, List.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new ProcessingException("Error deserializing a result List.", e);
        }
    }
}
