package com.avenuecode.exceptions.graph;

import com.avenuecode.models.Graph;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alexandre on 05/08/18.
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "graph not found")
public class GraphNotFoundException extends RuntimeException{
}
