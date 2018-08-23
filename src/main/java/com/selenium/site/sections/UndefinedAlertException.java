package com.selenium.site.sections;

public class UndefinedAlertException extends RuntimeException {
    public UndefinedAlertException(String s) {
        super(String.format("Undefined alert '%s' found on the screen", s));
    }
}
