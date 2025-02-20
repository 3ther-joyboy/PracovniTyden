package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Question;
import pup.quiz.server.repo.AnswerRepo;
import pup.quiz.server.repo.QuestionRepo;
import pup.quiz.server.repo.QuestionSetRepo;
import pup.quiz.server.repo.SessionRepo;
import pup.quiz.server.workers.QuestionWorker;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    QuestionSetRepo qs_rep;
    @Autowired
    QuestionRepo q_rep;
    @Autowired
    AnswerRepo a_rep;
    @Autowired
    SessionRepo s_rep;

   QuestionWorker qw = new QuestionWorker(qs_rep,q_rep,a_rep,s_rep);

    public static String Password = "1234";
    @PostMapping(value = "/post_question/{question}/{answer}/{answer_2}/{answer_3}/{answer_4}/{password}/{set}")
    public Long PostQuestion(@PathVariable(name = "question") String question,
                             @PathVariable(name = "answer") String answer1,
                             @PathVariable(name = "answer_2") String answer2,
                             @PathVariable(name = "answer_3") String answer3,
                             @PathVariable(name = "answer_4") String answer4,
                             @PathVariable(name = "password") String password,
                             @PathVariable(name = "set") Long set
    ) {

        System.out.printf("awdwadwa");
        if(Password.equals(password)) {
            System.out.printf("Question: " + question + "\n");
            System.out.printf("Answer 1: " + answer1 + "\n");
            System.out.printf("Answer 2: " + answer2 + "\n");
            System.out.printf("Answer 3: " + answer3 + "\n");
            System.out.printf("Answer 4: " + answer4 + "\n");



            return qw.CreateQuestion(question, new String[]{answer1, answer2, answer3, answer4}, new boolean[]{true, false, false, false}, set);
        }
        return 0L;
    }
}
