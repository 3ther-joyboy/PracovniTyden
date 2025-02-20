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
    QuestionSetRepo qs_rep;
    @Autowired
    QuestionRepo q_rep;
    @Autowired
    AnswerRepo a_rep;
    @Autowired
    SessionRepo s_rep;

    public Boolean DeleteQuestion(Long question) {
        try{
            q_rep.deleteById(question);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    // tady znova returni Iterable
    public Iterable<Question> GetQuestion() {
        return q_rep.findAll();
    }
    public Iterable<QuestionSets> GetSets() {
        return qs_rep.findAll();
    }
    public Set<Question> GetQuestionFrom(String session) {
        return s_rep.findByCode(session).Questions;
    }
    public Long CreateSet(String name) {
        QuestionSets set = new QuestionSets();
        set.Name = name;
        return qs_rep.save(set).Id;
    }
    public Long CreateQuestion(String question, String[] ans , boolean[] corr,Long set) {
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
