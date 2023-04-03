package model;

/**
 * Represents the exception that can occur when
 * printing the event log.
 */
// Code based on the LogException class from AlarmSystem in CPSC 210
public class LogException extends Exception {
    public LogException() {
        super("Error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}

