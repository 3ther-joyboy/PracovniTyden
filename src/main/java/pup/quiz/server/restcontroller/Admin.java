package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.PunishmentsSets;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.QuestionSets;
import pup.quiz.server.repo.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class Admin {

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



            return CreateQuestion(question, new String[]{answer1, answer2, answer3, answer4}, new boolean[]{true, false, false, false}, set);
        }
        return 0L;
    }






    ///  WORKER
    @Autowired
    QuestionSetRepo qs_rep;
    @Autowired
    QuestionRepo q_rep;
    @Autowired
    AnswerRepo a_rep;
    @Autowired
    SessionRepo s_rep;
    @Autowired
    private PunismentSetRepo p_rep;


    public Long CreateSetPunish(String name) {
        PunishmentsSets set = new PunishmentsSets();
        set.Name = name;
        return p_rep.save(set).Id;
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

    public Boolean DeleteQuestion(Long question) {
        try{
            q_rep.deleteById(question);
            return true;
        } catch (Exception e) {
        }
        return false;
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
