package com.example.HelloDemo.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.HelloDemo.service.RegisterResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Without Fields Name
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RegisterResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Collect error messages from validation exceptions
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
            .map(error -> error.getDefaultMessage()) // Get only the error message text
            .collect(Collectors.toList()); // Collect into a List

        // Create a response object with the list of error messages
        RegisterResponse response = new RegisterResponse("Validation Error", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
// For Fields Name 
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//public ResponseEntity<RegisterResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//    // Create a map to hold field names and error messages
//    Map<String, String> errors = new HashMap<>();
//    ex.getBindingResult().getAllErrors().forEach(error -> {
//        String fieldName = ((FieldError) error).getField();
//        String errorMessage = error.getDefaultMessage();
//        errors.put(fieldName, errorMessage);
//    });
//
//    // Collect errors into a list of strings with field names included
//    List<String> errorMessages = errors.entrySet().stream()
//        .map(entry -> entry.getKey() + ": " + entry.getValue())
//        .collect(Collectors.toList());
//
//    // Create a response object with the list of error messages
//    RegisterResponse response = new RegisterResponse("Validation Error", errorMessages);
//
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//}
//}

