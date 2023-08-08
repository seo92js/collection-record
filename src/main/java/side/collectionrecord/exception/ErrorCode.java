package side.collectionrecord.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 가입된 회원이 있습니다"),
    CATEGORY_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 등록된 카테고리가 있습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
