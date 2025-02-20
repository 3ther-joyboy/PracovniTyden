package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pup.quiz.server.Generator;
import pup.quiz.server.model.*;
import pup.quiz.server.repo.*;

import java.time.Instant;
import java.util.*;

@Service
public class SessionWorker {
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

    public UUID AddUser(String code,String pfp, String name) {
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
    // "Start"
    public void NextInQuestion(String SessionCode) {
        // TODO
        // vybere novou otázku a tu novou otázku odebere z listu možných otázek ze kterých vybýrá
        for (User i : GetUsersInSession(SessionCode)) {
            i.punishTimestamp = Instant.MAX;
            u_rep.save(i);
        }
    }
    public Set<User> GetUsersInSession(String SessionCode) {
        return rep.findByCode(SessionCode).Users;
    }
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
    public Session Generate(Long[] questions, Long[] punisments) {
        Session ses = new Session();
        ses.Code = Generator.GenerateCode();

        for (Long i : questions)
            for (Question que : qs_rep.findById(i).get().Questions) {
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
