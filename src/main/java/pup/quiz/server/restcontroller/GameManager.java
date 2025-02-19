package pup.quiz.server.restcontroller;

import org.springframework.web.bind.annotation.*;
import pup.quiz.server.Generator;
import pup.quiz.server.model.Session;
import pup.quiz.server.workers.SessionWorker;

@RestController
@RequestMapping("/host")
public class GameManager {

    @PostMapping(value = "/create_game")
    public String Create() {
        String code = Generator.GenerateCode();

        return code;
    }
}
