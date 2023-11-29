package side.collectionrecord.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResult {
    private final String code;
    private final String message;
}
