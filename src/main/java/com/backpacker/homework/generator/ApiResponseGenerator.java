package com.backpacker.homework.generator;

import com.backpacker.homework.code.ApiResponseCode;
import com.backpacker.homework.response.ApiResponse;

public class ApiResponseGenerator {

    private final static ApiResponse<Void> RESULT_SUCCESS = new ApiResponse<>(ApiResponseCode.SUCCESS);
    private final static ApiResponse<Void> RESULT_ERROR   = new ApiResponse<>(ApiResponseCode.UNKNOWN_ERROR);

    public static ApiResponse<Void> success(){
        return RESULT_SUCCESS;
    }

    public static <D> ApiResponse<D> success(D data){
        return new ApiResponse<>(ApiResponseCode.SUCCESS, null, data);
    }

    public static ApiResponse<Void> fail(ApiResponseCode code){
        return new ApiResponse<>(code);
    }

    public static ApiResponse<Void> fail(ApiResponseCode code, String msg){
        return new ApiResponse<>(code, msg, null);
    }

}
