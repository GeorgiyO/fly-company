package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ResponseContainer {

    private Object body;
    private int status;

    private ResponseContainer(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public static ResponseEntity<ResponseContainer> of(int status, Object body) {
        return new ResponseEntity<>(new ResponseContainer(status, body), HttpStatus.valueOf(status));
    }

    public static ResponseEntity<ResponseContainer> ok() {
        return ResponseContainer.of(200, "succeed");
    }

}
