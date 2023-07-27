package com.demo.user.exception;

import com.demo.user.enums.StatusCode;

public class CustomException extends RuntimeException {

    protected final StatusCode errorCode;

    public CustomException() {
        super(StatusCode.FAIL.getCodeMsg());
        this.errorCode = StatusCode.FAIL;
    }
    
    public CustomException(final StatusCode errorCode) {
        super(errorCode.getCodeMsg());
        this.errorCode = errorCode;
    }

    /**
     * Description Exception
     * @param detailedMessage
     */
    public CustomException(final String detailedMessage) {
        super(detailedMessage);
        this.errorCode = StatusCode.SYS_ERROR;
    }

    /**
     * Throw exception with Throwable
     * @param t Throwable
     */
    public CustomException(final Throwable t) {
        super(t);
        this.errorCode = StatusCode.SYS_ERROR;
    }

    /**
     *
     * @param errorCode
     * @param detailedMessage
     */
    public CustomException(final StatusCode errorCode, final String detailedMessage) {
        super(detailedMessage);
        this.errorCode = errorCode;
    }

    /**
     *
     * @param errorCode
     * @param t
     */
    public CustomException(final StatusCode errorCode, final Throwable t) {
        super(errorCode.getCodeMsg(), t);
        this.errorCode = errorCode;
    }

    /**
     *
     * @param detailedMessage
     * @param t
     */
    public CustomException(final String detailedMessage, final Throwable t) {
        super(detailedMessage, t);
        this.errorCode = StatusCode.SYS_ERROR;
    }

    /**
     *
     * @param errorCode
     * @param detailedMessage
     * @param t
     */
    public CustomException(final StatusCode errorCode, final String detailedMessage,
                                  final Throwable t) {
        super(detailedMessage, t);
        this.errorCode = errorCode;
    }

    public StatusCode getStatusCode() {
        return this.errorCode;
    }
    
}
