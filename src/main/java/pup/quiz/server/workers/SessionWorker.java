package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.Generator;
import pup.quiz.server.model.*;
import pup.quiz.server.repo.*;

import java.time.Instant;
import java.util.*;

public class SessionWorker {
    @Autowired
    static SessionRepo rep;
    @Autowired
    static PunismentSetRepo p_rep;
    @Autowired
    static QuestionSetRepo q_rep;
    @Autowired
    static UserRepo u_rep;

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
    public static void NextInQuestion(String SessionCode) {
        // TODO
        // vybere novou otázku a tu novou otázku odebere z listu možných otázek ze kterých vybýrá
        for (User i : GetUsersInSession(SessionCode)) {
            i.punishTimestamp = Instant.MAX;
            u_rep.save(i);
        }
    }
    public static Set<User> GetUsersInSession(String SessionCode) {
        return rep.findByCode(SessionCode).Users;
    }
    public static Iterable<User> GetPunishedUsers(String SessionCode,UUID code, int countOfPunished) {

        // TODO
        return null;
    }
    public static Question GetCurrentQuestion(String SessionCode) {
        return rep.findByCode(SessionCode).CurrentQuestion;
    }

    public static void UserAnswer(String SessionCode,UUID userCode,Long AnswerId) {
        Long correct = q_rep.getCorrectAnswer(GetCurrentQuestion(SessionCode).Id,AnswerId);
        if(correct != null && correct == 1L) {
            User usr = u_rep.findById(userCode).get();
            usr.Score++;
            usr.punishTimestamp = Instant.MIN;
            u_rep.save(usr);
        }else{
            User usr = u_rep.findById(userCode).get();
            usr.punishTimestamp = Instant.now();
            u_rep.save(usr);

        }
    }
    public static boolean SessionExists(String code) {
            return rep.findByCode(code) != null;
    }
    public static Session Generate(Long[] questions, Long[] punisments) {
        Session ses = new Session();
        ses.Code = Generator.GenerateCode();

        for (Long i : questions)
            for (Question que : q_rep.findById(i).get().Question) {
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
