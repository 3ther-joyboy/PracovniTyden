package pup.quiz.server.restcontroller;

import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Session;
import pup.quiz.server.workers.SessionWorker;
import java.util.Arrays;

@RestController
@RequestMapping("/host")
public class GameManager {

    @PostMapping(value = "/create_game")
    public String CreateSession(@RequestParam String questionIds, @RequestParam String punishmentIds) {
        Long[] questionIdsArray = Arrays.stream(questionIds.split(","))
                .map(Long::parseLong)
                .toArray(Long[]::new);

        Long[] punishmentIdsArray = Arrays.stream(punishmentIds.split(","))
                .map(Long::parseLong)
                .toArray(Long[]::new);

        String code = SessionWorker.Generate(questionIdsArray, punishmentIdsArray).Code;

        return code;
    }

    @PostMapping(value = "/{sessionId}/start")
    public void StartSession() {

    }
}
