package com.studerw.tda.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Convert between Java pojos and JSON. This class is thread safe.
 */
public class DefaultMapper {
  private final static ObjectMapper defaultMapper = new ObjectMapper();

  /**
   * Convert object to JSON string.
   * Use {@link FormatUtils#prettyFormat(String)} to pretty format the returned JSON string.
   * @param object Java POJO to serialize into JSON
   * @return JSON representation of the object
   */
  public static String toJson(Object object) {
    try {
      return defaultMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *
   * Convert object from JSON to POJO
   * @param json string of json
   * @param clazz the class type
   * @param <T> the type to marshall
   * @return a deserialzed java POJO
   */
  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return defaultMapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *
   * Convert object from JSON to POJO.
   * @param in InputStream of json, guaranteed to be closed upon return.
   * @param clazz the class type
   * @param <T> the type to marshall
   * @return a deserialzed java POJO
   */
  public static <T> T fromJson(InputStream in, Class<T> clazz) {
    try(BufferedInputStream bIn = new BufferedInputStream(in)) {
      T t = defaultMapper.readValue(in, clazz);
      return t;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *
   * Convert object from JSON to POJO.
   * @param in InputStream of json, guaranteed to be closed upon return.
   * @param typeReference
   * @param <T> the type to marshall
   * @return a deserialzed java POJO
   */
  public static <T> T fromJson(InputStream in, TypeReference<T> typeReference) {
    try(BufferedInputStream bIn = new BufferedInputStream(in)) {
      T t = defaultMapper.readValue(in, typeReference);
      return t;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}