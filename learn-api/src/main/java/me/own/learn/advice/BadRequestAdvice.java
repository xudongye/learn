package me.own.learn.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author Christopher.Wang 2017/3/19.
 */
@ControllerAdvice
public class BadRequestAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handle(MethodArgumentNotValidException me) {
        List<ObjectError> allErrors = me.getBindingResult().getAllErrors();
        return new ResponseEntity<>(allErrors, HttpStatus.BAD_REQUEST);
    }
}
