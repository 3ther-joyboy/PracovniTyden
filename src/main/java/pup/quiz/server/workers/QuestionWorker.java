package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.QuestionSets;
import pup.quiz.server.repo.AnswerRepo;
import pup.quiz.server.repo.QuestionRepo;
import pup.quiz.server.repo.QuestionSetRepo;
import pup.quiz.server.repo.SessionRepo;

import java.util.Set;

public class QuestionWorker {
    @Autowired
    static QuestionSetRepo qs_rep;
    @Autowired
    static QuestionRepo q_rep;
    @Autowired
    static AnswerRepo a_rep;
    @Autowired
    static SessionRepo s_rep;

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
        que.Set = qs_rep.findById(set).get();

        for (int i = 0; i < ans.length; i++) {
            Answer as = new Answer(ans[i],corr[i]);
            a_rep.save(as);
            que.Answers.add(as);
        }
        return q_rep.save(que).Id;
    }
}
