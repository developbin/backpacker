package com.backpacker.homework.exception;

import com.backpacker.homework.code.ApiResponseCode;
import com.backpacker.homework.generator.ApiResponseGenerator;
import com.backpacker.homework.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
public class HomeworkApiExceptionHandler {

    /**
     * @Valid 가 선언된 객체의 유효성이 옳지 않은 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = MessageFormat.format("{0}는 {1}", fieldError.getField(), fieldError.getDefaultMessage());

        log.error("ApiExceptionHandler > MethodArgumentNotValidException > userMessage:{}", message);
        log.error("ApiExceptionHandler > MethodArgumentNotValidException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 비밀번호가 잘못된 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnMatchedPasswordException.class)
    public ResponseEntity<ApiResponse> handleException(UnMatchedPasswordException e) {
        String message = e.getMessage();

        log.error("ApiExceptionHandler > UnMatchedPasswordException > userMessage:{}", message);
        log.error("ApiExceptionHandler > UnMatchedPasswordException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 이메일이 존재하지 않은 경우
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistEmailException.class)
    public ResponseEntity<ApiResponse> handleException(NotExistEmailException e) {
        String message = e.getMessage();

        log.error("ApiExceptionHandler > NotExistEmailException > userMessage:{}", message);
        log.error("ApiExceptionHandler > NotExistEmailException > errorMessage:{}", e.getMessage());

        ApiResponse apiResponse = ApiResponseGenerator.fail(ApiResponseCode.BAD_REQUEST_ERROR, message);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
