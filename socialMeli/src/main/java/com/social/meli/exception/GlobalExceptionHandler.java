package com.social.meli.exception;

import com.social.meli.dto.response.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDto> NotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto(e.getMessage()));
    }
    @ExceptionHandler(UserIsNotVendorException.class)
    public ResponseEntity<MessageDto> userIsNotVendorException(UserIsNotVendorException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageDto(e.getMessage()));
    }
    @ExceptionHandler(UserFollowException.class)
    public ResponseEntity<MessageDto> userFollowException(UserFollowException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageDto(e.getMessage()));
    }
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<MessageDto> invalidDataException(InvalidDataException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(e.getMessage()));
    }

}
