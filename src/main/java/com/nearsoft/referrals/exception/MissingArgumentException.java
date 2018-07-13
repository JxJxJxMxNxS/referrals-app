package com.nearsoft.referrals.exception;

public class MissingArgumentException extends RuntimeException {
    public MissingArgumentException(String s) {
        super(s);
    }

    public MissingArgumentException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
