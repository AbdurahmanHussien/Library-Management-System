package com.springboot.librarysystem.exception;


import com.springboot.librarysystem.dto.BundleMessageDto;
import com.springboot.librarysystem.dto.response.ErrorResponse;
import com.springboot.librarysystem.service.BundleTranslatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalException {

    private final BundleTranslatorService bundleTranslator;

    public GlobalException(BundleTranslatorService bundleTranslator) {
        this.bundleTranslator = bundleTranslator;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
       BundleMessageDto messages = bundleTranslator.getBundleMessages(ex.getMessage());
        ErrorResponse apiError = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
               HttpStatus.NOT_FOUND.getReasonPhrase(),
               messages);

            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateFieldException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateFieldException ex) {
        BundleMessageDto messages = bundleTranslator.getBundleMessages(ex.getMessage());
        ErrorResponse apiError = new ErrorResponse(HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                messages);

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        BundleMessageDto messages = bundleTranslator.getBundleMessages(ex.getMessage());

        ErrorResponse apiError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messages);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(errorField -> {
                    var messages = bundleTranslator.getBundleMessages(errorField.getDefaultMessage());
                    return new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            errorField.getField(),
                            messages
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(BadCredentialsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


    // for global exception
    @ExceptionHandler(RuntimeException .class)
    public ResponseEntity<ErrorResponse> handleGlobal(RuntimeException  ex) {
        BundleMessageDto messages = bundleTranslator.getBundleMessages(ex.getMessage());
        ErrorResponse apiError = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messages);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEnum(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid language. Allowed values: AR, EN, FR, ES, DE");
        return ResponseEntity.badRequest().body(error);
    }
}
