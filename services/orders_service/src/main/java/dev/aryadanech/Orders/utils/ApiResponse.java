package dev.aryadanech.Orders.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int status;
    private boolean isSuccess;
    private boolean isError;
    private String error;
    private String message;
    private T body;

    public static <T> ApiResponse<T> success(T body, String message) {
        return ApiResponse.<T>builder()
                .status(200)
                .isSuccess(true)
                .isError(false)
                .error(null)
                .message(message)
                .body(body)
                .build();
    }
}
