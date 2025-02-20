package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.QuestionSets;
import pup.quiz.server.repo.AnswerRepo;
import pup.quiz.server.repo.QuestionRepo;
import pup.quiz.server.repo.QuestionSetRepo;
import pup.quiz.server.repo.SessionRepo;

import java.util.Set;

public class QuestionWorker {
    static QuestionSetRepo qs_rep;
    static QuestionRepo q_rep;
    static AnswerRepo a_rep;
    static SessionRepo s_rep;
    @Autowired
    public QuestionWorker(QuestionSetRepo qs_rep, QuestionRepo q_rep, AnswerRepo a_rep, SessionRepo s_rep) {
        this.qs_rep = qs_rep;
        this.q_rep = q_rep;
        this.a_rep = a_rep;
        this.s_rep = s_rep;
    }

    public static Boolean DeleteQuestion(Long question) {
        try{
            q_rep.deleteById(question);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    // tady znova returni Iterable
    public static Iterable<Question> GetQuestion() {
        return q_rep.findAll();
    }
    public static Iterable<QuestionSets> GetSets() {
        return qs_rep.findAll();
    }
    public static Set<Question> GetQuestionFrom(String session) {
        return s_rep.findByCode(session).Questions;
    }
    public static Long CreateSet(String name) {
        QuestionSets set = new QuestionSets();
        set.Name = name;
        return qs_rep.save(set).Id;
    }
    public static Long CreateQuestion(String question, String[] ans , boolean[] corr,Long set) {
        Question que = new Question();
        que.Question = question;

        for (int i = 0; i < ans.length; i++) {
            Answer as = new Answer(ans[i],corr[i]);
            a_rep.save(as);
            que.Answers.add(as);
        }
        Long id = q_rep.save(que).Id;

        QuestionSets asdf = qs_rep.findById(set).get();
        asdf.Questions.add(que);
        qs_rep.save(asdf);
        return id;
    }
}
