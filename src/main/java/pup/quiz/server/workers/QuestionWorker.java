package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.model.Question;
import pup.quiz.server.repo.AnswerRepo;
import pup.quiz.server.repo.QuestionRepo;
import pup.quiz.server.repo.QuestionSetRepo;

public class QuestionWorker {
    @Autowired
    static QuestionSetRepo qs_rep;
    @Autowired
    static QuestionRepo q_rep;
    @Autowired
    static AnswerRepo a_rep;

    public static Long CreateQuestion(String question, String[] ans , boolean[] corr) {
        Question que = new Question();
        que.Question = question;

//        for (int i = 0; i < ans.length; i++) {
//            Answer as = new Answer();
//            a_rep.save()
//            que.Answers.add(a_rep.findById(i).get());
//        }
        return q_rep.save(que).Id;
    }
}
