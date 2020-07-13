package framework.exceptions;

import io.restassured.response.Response;

public class ApiException extends RuntimeException {
    private Response response;
    private int statusCode;
    private ExceptionEnumConstants.ApiExceptionDescription exceptionReason;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Response response) {
        super(message);
        this.response = response;
        this.statusCode = response.getStatusCode();
    }

    public ApiException(String message, ExceptionEnumConstants.ApiExceptionDescription exceptionReason) {
        super(message);
        this.exceptionReason = exceptionReason;
    }
}
