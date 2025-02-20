package pup.quiz.server.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class UserManager {

    @GetMapping(value = "/join/{code}_{name}_{avatar}")
    public ResponseEntity<String> JoinSession(@PathVariable(name = "code") String code,
                                              @PathVariable(name = "name") String userName,
                                              @PathVariable(name = "avatar") String imgUrl) {

        // ADDING USER TO SESSION DATABASE
        //SessionWorker.AddUser(code, userName, imgUrl);

        return ResponseEntity.ok("Successfully added to game!, Code: " + code + " Name: " + userName + " Icon: " + imgUrl);
    }

    @PostMapping(value = "/{code}/{index}/{userID}/submit_answer")
    public void SubmitAnswer(@PathVariable(name = "code") String code,
                             @PathVariable(name = "index") int index,
                             @PathVariable(name = "userID") UUID id) {



    }
}
