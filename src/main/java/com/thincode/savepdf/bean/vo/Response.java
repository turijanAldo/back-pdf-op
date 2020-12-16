package com.thincode.savepdf.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Hash code.
 *
 * @return the int
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @JsonInclude(value = Include.NON_NULL)
public class Response<T> extends SuperObject {

  private String message;
  private String timestamp;
  private String details;
  private int code;
  private T data;

}
