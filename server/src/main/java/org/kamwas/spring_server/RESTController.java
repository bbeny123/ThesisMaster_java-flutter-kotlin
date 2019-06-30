package org.kamwas.spring_server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class RESTController {

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ok().build();
    }

    @PostMapping("/post")
    public ResponseEntity<?> post() {
        return ok().build();
    }

}
