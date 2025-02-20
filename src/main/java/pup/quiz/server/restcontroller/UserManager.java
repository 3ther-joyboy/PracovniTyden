package pup.quiz.server.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pup.quiz.server.workers.SessionWorker;
import pup.quiz.server.workers.UsersWorker;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class UserManager {

    @PostMapping(value = "/join/{code}_{nane}_{avatar}")
    public ResponseEntity<String> JoinSession(@PathVariable(name = "code") String code,
                                              @PathVariable(name = "name") String userName,
                                              @PathVariable(name = "avatar") String imgUrl) {

        // ADDING USER TO SESSION DATABASE
        SessionWorker.AddUser(code, userName, imgUrl);

        return ResponseEntity.ok("Successfully added to game!");
    }

    @PostMapping(value = "/{sessionId}/submit_answer")
    public void Submit() {
    }
}
