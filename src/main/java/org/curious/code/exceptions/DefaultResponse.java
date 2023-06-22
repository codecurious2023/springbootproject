package org.curious.code.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultResponse {
    private int id;
    private int statusCode;
    private String msg;
}
