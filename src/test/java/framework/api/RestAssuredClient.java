package framework.api;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import framework.exceptions.ApiException;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;

public class RestAssuredClient {
    private static Logger logger = LoggerFactory.getLogger(RestAssuredClient.class);

    public static <R> R convertResponse(Response response, Class<R> className) {
        checkStatusCode(response);
        return getConvertedResponseBody(response, className);
    }

    private static void checkStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        switch (statusCode) {
            case HttpURLConnection.HTTP_OK:
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                throw new AssertionError(String.format("Response failed with '%s'.%nRaw response:%n%s",
                        response.getStatusLine(), response.prettyPrint()));
            default:
                throw new ApiException(String.format("Status code '%s' is not successful. Response%n%s",
                        statusCode, response.prettyPrint()), response);
        }
    }

    private static <R> R getConvertedResponseBody(Response response, Class<R> className) {
        try {
            return response.getBody().as(className);
        } catch (Exception e) {
            if (e instanceof UnrecognizedPropertyException) {
                String msg = String.format("Cannot cast response to class '%s'.%nResponse:%n%s%nReason:%n%s",
                        className, response.prettyPrint(), e.getMessage());
                logger.error(msg);
                String errorMessage = String.format("Unrecognised field '%s' in class '%s'",
                        ((UnrecognizedPropertyException) e).getPropertyName(), ((UnrecognizedPropertyException) e).getReferringClass());
                throw new AssertionError(errorMessage);
            } else {
                throw new ApiException(String.format("Something went wrong.%nRaw response:%n%s%nError message:%n%s",
                        response.prettyPrint(), e.getMessage()), response);
            }
        }
    }
}
