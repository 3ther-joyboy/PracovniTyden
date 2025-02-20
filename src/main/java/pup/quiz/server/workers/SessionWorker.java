package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pup.quiz.server.Generator;
import pup.quiz.server.model.*;
import pup.quiz.server.repo.*;

import java.time.Instant;
import java.util.*;

public class SessionWorker {
    static SessionRepo rep;
    static PunismentSetRepo p_rep;
    static QuestionSetRepo qs_rep;
    static QuestionRepo q_rep;
    static UserRepo u_rep;

    @Autowired
    public SessionWorker(SessionRepo rep, PunismentSetRepo p_rep, QuestionSetRepo qs_rep ,QuestionRepo q_rep, UserRepo u_rep) {
        this.rep = rep;
        this.p_rep = p_rep;
        this.qs_rep = qs_rep;
        this.q_rep = q_rep;
        this.u_rep = u_rep;
    }

    public static UUID AddUser(String code,String pfp, String name) {
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
    public static Iterable<User> GetPunishedUsers(UUID code, int countOfPunished) {
        return u_rep.punishedUsers(code,countOfPunished);
    }
    public static Question GetCurrentQuestion(String SessionCode) {
        return rep.findByCode(SessionCode).CurrentQuestion;
    }

    public static void UserAnswer(String SessionCode,UUID userID,Long AnswerId) {
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
    public static boolean SessionExists(String code) {
            return rep.findByCode(code) != null;
    }
    public static Session Generate(Long[] questions, Long[] punisments) {
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
