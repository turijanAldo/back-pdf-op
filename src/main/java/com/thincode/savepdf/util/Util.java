package com.thincode.savepdf.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thincode.savepdf.bean.vo.Response;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * The Class Util.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class Util {

  private final ObjectMapper mapper;

  /**
   * Convert object to string.
   *
   * @param obj the obj
   * @return the string
   */
  public String convertObjectToString(Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (Exception ex) {
      log.warn("Error when try to convert object to string", ex);
    }
    return null;
  }

  /**
   * Convert string to object.
   *
   * @param <T> the generic type
   * @param json the json
   * @param clazz the clazz
   * @return the t
   */
  public <T> T convertStringToObject(String json, Class<T> clazz) {
    try {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper.readValue(json, clazz);
    } catch (Exception ex) {
      log.warn("Error when try to convert string to object", ex);
    }
    return null;
  }

  /**
   * Convert string to object list.
   *
   * @param <T> the generic type
   * @param json the json
   * @param type the type
   * @return the t
   */
  public <T> T convertStringToObjectList(String json, TypeReference<T> type) {
    try {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper.readValue(json, type);
    } catch (Exception ex) {
      log.warn("Error when try to convert string to object list", ex);
    }
    return null;
  }


  /**
   * Copy fields.
   *
   * @param <T> the generic type
   * @param source the source
   * @param destination the destination
   * @return the t
   */
  public <T> T copyFields(Object source, Class<T> destination) {
    try {
      Object result = destination.newInstance();
      BeanUtils.copyProperties(source, result);
      return destination.cast(result);
    } catch (IllegalAccessException | InstantiationException ex) {
      log.error("Error when try to copy field values from source {} to destination {}",
          source.getClass().getName(), destination.getName(), ex);
    }
    return null;
  }

  /**
   * Gets the stack trace.
   *
   * @param throwable the throwable
   * @return the stack trace
   */
  public String getStackTrace(Throwable throwable) {
    StringWriter sw = new StringWriter();
    try {
      throwable.printStackTrace(new PrintWriter(sw));
      return sw.toString();
    } catch (Exception ex) {
      log.warn("Can't get trace", ex);
    }
    return null;
  }

  /**
   * Gets the timestamp.
   *
   * @return the timestamp
   */
  public String getTimestamp() {
    return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
  }


  /**
   * Extract token.
   *
   * @param authorization the authorization
   * @return the string
   */
  public String extractToken(String authorization) {
    log.debug("Authorization header information {}", authorization);
    return authorization != null ? authorization.substring(7) : null;
  }

  /**
   * Sets the data.
   *
   * @param <T> the generic type
   * @param message the message
   * @param details the details
   * @return the response
   */
  public <T> Response<T> setMessageAndDetails(String message, String details) {
    Response<T> response = new Response<>();
    response.setTimestamp(getTimestamp());
    response.setMessage(message);
    response.setDetails(details);
    return response;
  }

  /**
   * Sets the data response.
   *
   * @param <T> the generic type
   * @param data the data
   * @return the response
   */
  public <T> Response<T> setDataResponse(T data) {
    Response<T> response = setDataResponse();
    response.setData(data);
    return response;
  }

  /**
   * Sets the data response.
   *
   * @param <T> the generic type
   * @return the response
   */
  private <T> Response<T> setDataResponse() {
    Response<T> response = new Response<>();
    response.setTimestamp(getTimestamp());
    response.setMessage("OK");
    return response;
  }

}
