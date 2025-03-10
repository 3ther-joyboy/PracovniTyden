package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.Generator;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.Session;
import pup.quiz.server.model.User;
import pup.quiz.server.repo.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/host")
public class GameManager {

    @GetMapping(value = "/create_game")
    public String CreateSession(@RequestBody Long[][] penis) {
        String code = Generate(penis[0], penis[0]).Code;

        return code;
    }

    ///  WOKERS
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

    public Iterable<User> GetPunishedUsers(UUID code, int countOfPunished) {
        return u_rep.punishedUsers(code,countOfPunished);
    }
    public Question GetCurrentQuestion(String SessionCode) {
        return rep.findByCode(SessionCode).CurrentQuestion;
    }

    public void UserAnswer(String SessionCode,UUID userID,Long AnswerId) {
        Long correct = q_rep.getCorrectAnswer(GetCurrentQuestion(SessionCode).Id,AnswerId);
        if(correct != null && correct == 1L) {
            User usr = u_rep.findById(userID).get();
            usr.Score++;
            usr.punishTimestamp = Instant.MIN;
            u_rep.save(usr);
        }else{
            User usr = u_rep.findById(userID).get();
            usr.punishTimestamp = Instant.now();
            u_rep.save(usr);

        }
    }
    public boolean SessionExists(String code) {
        return rep.findByCode(code) != null;
    }
    public Set<User> GetUsersInSession(String SessionCode) {
        return rep.findByCode(SessionCode).Users;
    }
    // "Start"
    public void NextInQuestion(String SessionCode) {
        Session s = rep.findByCode(SessionCode);
        Question q = q_rep.randomInSession(s.Id);
        s.CurrentQuestion = q;
        s.Questions.remove(q);
        rep.save(s);
        for (User i : GetUsersInSession(SessionCode)) {
            i.punishTimestamp = Instant.MAX;
            u_rep.save(i);
        }
    }
    public UUID AddUser(String code, String pfp, String name) {
        Session joiningSession = rep.findByCode(code);
        if(joiningSession != null) {
            User usr = new User();
            usr.Name = name;
            usr.ProfilePicture = pfp;
            usr.session = joiningSession;

            return u_rep.save(usr).Id;
        }
        return null;
    }
    public Iterable<Punishments> RandomPunishment(String code, int coutOfUsers) {

    }
    public Session Generate(Long[] questions, Long[] punisments) {
        Session ses = new Session();
        ses.Code = Generator.GenerateCode();

        for (Long i : questions)
            for (Question que : qs_rep.findById(i).get().Questions) {
                ses.CurrentQuestion = que;
                ses.Questions.add(que);
            }
        for (Long i : punisments)
            for (Punishments pun : p_rep.findById(i).get().Punish) {
                ses.Punisment.add(pun);
            }


        rep.save(ses);
        return ses;
    }
}
