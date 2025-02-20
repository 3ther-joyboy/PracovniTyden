package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Session;
import pup.quiz.server.repo.*;
import pup.quiz.server.workers.SessionWorker;
import java.util.Arrays;

@RestController
@RequestMapping("/host")
public class GameManager {

    @Autowired
    SessionRepo rep;
    @Autowired
    PunismentSetRepo p_rep;
    @Autowired
    QuestionSetRepo qs_rep;
    @Autowired
    QuestionRepo q_rep;
    @Autowired
    UserRepo u_rep;

   SessionWorker sw = new SessionWorker(rep, p_rep,qs_rep,q_rep,u_rep);

    @PostMapping(value = "/create_game")
    public String CreateSession(@RequestParam String questionIds, @RequestParam String punishmentIds) {
        Long[] questionIdsArray = Arrays.stream(questionIds.split(","))
                .map(Long::parseLong)
                .toArray(Long[]::new);

        Long[] punishmentIdsArray = Arrays.stream(punishmentIds.split(","))
                .map(Long::parseLong)
                .toArray(Long[]::new);

        String code = sw.Generate(questionIdsArray, punishmentIdsArray).Code;

        return code;
    }

    @PostMapping(value = "/{sessionId}/start")
    public void StartSession() {

    }
}
