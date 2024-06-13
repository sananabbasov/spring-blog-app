package az.edu.compar.exceptions;

import az.edu.compar.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiException = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiException, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiException = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiException, HttpStatus.BAD_REQUEST);
    }
}
