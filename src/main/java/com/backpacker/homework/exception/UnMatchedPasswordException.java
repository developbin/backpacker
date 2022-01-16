package com.backpacker.homework.exception;

public class UnMatchedPasswordException extends RuntimeException {

    public UnMatchedPasswordException(){ super("비밀번호가 일치하지 않습니다."); }

    public UnMatchedPasswordException(String message){ super(message);}

}
