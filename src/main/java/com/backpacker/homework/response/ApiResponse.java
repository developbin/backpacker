package com.backpacker.homework.response;

import com.backpacker.homework.code.ApiResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponse<T> {

    private String code;

    private String message;

    private T data;

    public ApiResponse(ApiResponseCode apiResponseCode){
        this(apiResponseCode, null, null);
    }

    public ApiResponse(ApiResponseCode apiResponseCode, String responseMessage, T data) {
        this.code = apiResponseCode.getCode();
        this.message = (responseMessage == null ? apiResponseCode.getDefaultMessage() : responseMessage);
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
