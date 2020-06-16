package com.bugevil.mhelmi.carshowroom.utils.result;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

@Keep
public class Result<T> {
  private ResultType resultType;
  @Nullable
  private T data;
  @Nullable
  private String message;

  public Result(ResultType resultType, @Nullable T data, @Nullable String message) {
    this.resultType = resultType;
    this.data = data;
    this.message = message;
  }

  public Result(ResultType resultType, @Nullable T data) {
    this.resultType = resultType;
    this.data = data;
  }

  public Result(ResultType resultType, @Nullable String message) {
    this.resultType = resultType;
    this.message = message;
  }

  public ResultType getResultType() {
    return resultType;
  }

  @Nullable
  public T getData() {
    return data;
  }

  @Nullable
  public String getMessage() {
    return message;
  }

  public static <T> Result<T> success() {
    return new Result<>(ResultType.SUCCESS, null);
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(ResultType.SUCCESS, data);
  }

  public static <T> Result<T> success(T data, String message) {
    return new Result<>(ResultType.SUCCESS, data, message);
  }

  public static <T> Result<T> successMessage(String message) {
    return new Result<>(ResultType.SUCCESS, message);
  }

  public static <T> Result<T> error(String message) {
    return new Result<>(ResultType.ERROR, message);
  }

  public static <T> Result<T> error() {
    return new Result<>(ResultType.ERROR, null);
  }

  public static <T> Result<T> connectionError() {
    return new Result<>(ResultType.CONNECTION_ERROR, null);
  }

  public static <T> Result<T> emptyDataError() {
    return new Result<>(ResultType.EMPTY_DATA, null);
  }

  public static <T> Result<T> validationError(T data, String message) {
    return new Result<>(ResultType.VALIDATION_ERROR, data, message);
  }

  public static <T> Result<T> validationError(T data) {
    return new Result<>(ResultType.VALIDATION_ERROR, data);
  }

  public static <T> Result<T> authFailedError(T data) {
    return new Result<>(ResultType.AUTH_FAILED_ERROR, data);
  }

  public static <T> Result<T> authFailedError() {
    return new Result<>(ResultType.AUTH_FAILED_ERROR, null);
  }

  public static <T> Result<T> loading() {
    return new Result<>(ResultType.LOADING, null);
  }
}
