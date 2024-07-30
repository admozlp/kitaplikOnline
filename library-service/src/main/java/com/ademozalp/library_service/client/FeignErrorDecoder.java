package com.ademozalp.library_service.client;

import com.ademozalp.library_service.exception.BookNotFoundException;
import com.ademozalp.library_service.exception.ErrorMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage errorMessage;
        try(InputStream body = response.body().asInputStream()){
            errorMessage = new ErrorMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());

        }catch (IOException exception){
            return new RuntimeException("Error while decoding response", exception);
        }

        return switch (response.status()) {
            case 404 -> new BookNotFoundException(errorMessage, "Book not found");
            case 400 -> new RuntimeException("Bad request");
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
