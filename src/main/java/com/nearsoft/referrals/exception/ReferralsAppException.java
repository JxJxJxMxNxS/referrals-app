package com.nearsoft.referrals.exception;

public class ReferralsAppException extends RuntimeException {
    public ReferralsAppException(String s) {
        super(s);
    }

    public ReferralsAppException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
