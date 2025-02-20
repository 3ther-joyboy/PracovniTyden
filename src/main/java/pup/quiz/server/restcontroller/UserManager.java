package pup.quiz.server.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class UserManager {
    @PostMapping(value = "/{sessionId}/join/{userId}")
    public ResponseEntity<String> joinSession(@PathVariable Long sessionId, @PathVariable UUID userId) {

        return null;
    }

    @PostMapping(value = "/{sessionId}/submit_answer")
    public void Submit() {

    }
}
