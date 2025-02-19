package pup.quiz.server.restcontroller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Admin {
    @PostMapping(value = "/post_question/{question}_{answer}_{answer_2}_{answer_3}_{answer_4}")
    public void PostQuestion(@PathVariable(name = "question") String question,
                             @PathVariable(name = "answer") String answer1,
                             @PathVariable(name = "answer_2") String answer2,
                             @PathVariable(name = "answer_3") String answer3,
                             @PathVariable(name = "answer_4") String answer4) {
        System.out.printf("Question: " + question + "\n");
        System.out.printf("Answer 1: " + answer1 + "\n");
        System.out.printf("Answer 2: " + answer2 + "\n");
        System.out.printf("Answer 3: " + answer3 + "\n");
        System.out.printf("Answer 4: " + answer4 + "\n");
    }
}
