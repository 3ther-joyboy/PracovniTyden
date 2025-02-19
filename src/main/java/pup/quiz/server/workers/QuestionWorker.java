package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.model.Question;
import pup.quiz.server.repo.AnswerRepo;
import pup.quiz.server.repo.QuestionSetRepo;

public class QuestionWorker {
    @Autowired
    static QuestionSetRepo q_rep;
    @Autowired
    static AnswerRepo a_rep;

    public Long CreateQuestion(String question) {
        Question que = new Question();
        que.Question = question;


//        return q_rep.save(que).Id;
        return 0L;
    }
}
