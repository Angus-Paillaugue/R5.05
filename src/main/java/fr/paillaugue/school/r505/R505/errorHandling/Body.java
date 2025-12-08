package fr.paillaugue.school.r505.R505.errorHandling;

import java.util.Map;

public class Body {
  public static <T> T parseBody(Map<String, Object> body, String key) throws IllegalArgumentException {
    if(!body.containsKey(key)) {
      throw new IllegalArgumentException("Missing body property: " + key);
    }
    // Try to cast the value to the expected type
    try {
      @SuppressWarnings("unchecked")
      T value = (T) body.get(key);
      return value;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid type for body property: " + key);
    }
  }
}
