package framework.exceptions;

import io.restassured.response.Response;

public class ApiException extends RuntimeException {
    private Response response;
    private int statusCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Response response) {
        super(message);
        this.response = response;
        this.statusCode = response.getStatusCode();
    }
}
