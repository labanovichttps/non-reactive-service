package com.labanovich.product.exception;

import java.time.Instant;

public record ErrorResponse(Instant time,
                            int httpStatus,
                            String errorMessage) {
}
