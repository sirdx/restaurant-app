package com.github.sirdx.restaurantapp.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}
