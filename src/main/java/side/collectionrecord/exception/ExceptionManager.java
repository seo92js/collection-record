package side.collectionrecord.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    //form action에 대한 대응 아직 생각 못함.
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(CustomException e){
        return ErrorResponse.toResponseEntity(e);
    }

    //아직 Custom 하진 못했다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){

        return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
    }
}
