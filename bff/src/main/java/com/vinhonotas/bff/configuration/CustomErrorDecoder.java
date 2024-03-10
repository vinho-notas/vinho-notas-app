package com.vinhonotas.bff.configuration;

import com.vinhonotas.bff.application.services.exceptions.BadRequestException;
import com.vinhonotas.bff.application.services.exceptions.NotFoundException;
import com.vinhonotas.bff.utils.MessagesConstants;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException(MessagesConstants.BAD_REQUEST);
            case 404 -> new NotFoundException(MessagesConstants.NOT_FOUND);
            default -> new Exception(response.reason());
        };
    }
}
