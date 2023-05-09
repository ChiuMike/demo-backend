package com.example.demobackend.http;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> implements Serializable {

    private Integer status;

    private String message;

    private T data;

    public Result(@NonNull final ResultStatus resultStatus) {
        this.status = resultStatus.status();
        this.message = resultStatus.message();
    }

    public void setData(@NonNull T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public static class ResultBuilder<T> {

        public ResultBuilder<T> resultStatus(@NonNull final ResultStatus resultStatus) {
            this.status = resultStatus.status();
            this.message = resultStatus.message();
            return this;
        }
    }

    public static <T> Result<T> of(@NonNull final ResultStatus resultStatus) {
        return Result.<T>builder()
                .resultStatus(resultStatus)
                .build();
    }

    public static <T> Result<T> ok() {
        return Result.<T>builder()
                .resultStatus(ResultStatus.OK)
                .build();
    }

    public static <T> Result<T> ok(final T data) {
        return Result.<T>builder()
                .resultStatus(ResultStatus.OK)
                .data(data)
                .build();
    }

    public static <T> Result<T> failure() {
        return Result.<T>builder()
                .resultStatus(ResultStatus.BAD_REQUEST)
                .build();

    }

    public static <T> Result<T> forbidden() {
        return Result.<T>builder()
                .resultStatus(ResultStatus.FORBIDDEN)
                .build();
    }

    public static <T> Result<T> notFound() {
        return Result.<T>builder()
                .resultStatus(ResultStatus.NOT_FOUND)
                .build();
    }
}
