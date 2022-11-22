package com.homework.workmotion.generated.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets EmployeeState
 */
public enum EmployeeState {
  
  ADDED("ADDED"),
  
  IN_CHECK("IN-CHECK"),
  
  APPROVED("APPROVED"),
  
  ACTIVE("ACTIVE");

  private String value;

  EmployeeState(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EmployeeState fromValue(String value) {
    for (EmployeeState b : EmployeeState.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

