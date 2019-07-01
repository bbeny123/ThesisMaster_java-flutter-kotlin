package org.kamwas.spring_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class RESTController {

    private Logger logger = LoggerFactory.getLogger(RESTController.class);

    @GetMapping("/")
    public ResponseEntity<?> get() {
//        logger.info("GET called");
        return ok(new User(1L, "user", "user@user", "user", 30));
    }

    @PostMapping("/")
    public ResponseEntity<?> post(@RequestBody User user) {
//        logger.info("POST called - " + user.toString());
        return ok().build();
    }

}
