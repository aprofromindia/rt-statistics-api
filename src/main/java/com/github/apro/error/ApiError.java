package com.github.apro.error;

import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;

/** Created by achoudh on 19/03/2017. */
@Getter
class ApiError {
    private final Error code;
    private final String message;

    ApiError(Error code, Exception ex) {
        this.code = code;
        this.message = ExceptionUtils.getRootCauseMessage(ex);
    }

    @Override
    public String toString() {
        return code.getCode() + " : " + message;
    }
}
