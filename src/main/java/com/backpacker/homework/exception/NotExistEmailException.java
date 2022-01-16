package com.backpacker.homework.exception;

public class NotExistEmailException extends RuntimeException {

    public NotExistEmailException(){ super("존재하지 않는 이메일입니다."); }

    public NotExistEmailException(String message){ super(message);}

}
