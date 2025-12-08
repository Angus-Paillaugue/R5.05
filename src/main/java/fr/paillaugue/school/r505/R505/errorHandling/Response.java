package fr.paillaugue.school.r505.R505.errorHandling;

import org.springframework.http.ResponseEntity;

public class Response<T> {
  private ResponseStatus status;
  private T data;
  private String error;

  public Response(ResponseStatus status, T data) {
    this.status = status;
    this.data = data;
    this.error = null;
  }

  public Response(ResponseStatus status) {
    this.status = status;
    this.data = null;
    this.error = null;
  }

  public Response(ResponseStatus status, String error) {
    this.status = status;
    this.data = null;
    this.error = error;
  }

  public static <T> ResponseEntity<Response<T>> success(T data) {
    return ResponseEntity.ok(new Response<>(ResponseStatus.SUCCESS, data));
  }

  public static <T> ResponseEntity<Response<T>> error(String error) {
    return ResponseEntity.badRequest().body(new Response<>(ResponseStatus.ERROR, error));
  }

  public static <T> ResponseEntity<Response<T>> error(Exception error) {
    return Response.error(error.getMessage());
  }

  public ResponseStatus getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }

  public String getError() {
    return error;
  }
}
