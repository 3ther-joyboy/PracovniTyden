package pup.quiz.server.restcontroller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.Generator;
import pup.quiz.server.model.*;
import pup.quiz.server.repo.PunishmentRepo;
import pup.quiz.server.repo.QuestionRepo;
import pup.quiz.server.repo.SessionRepo;
import pup.quiz.server.repo.UserRepo;
import pup.quiz.server.workers.QuestionWorker;
import pup.quiz.server.workers.UsersWorker;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/servertest")
@CrossOrigin(origins = "*")
public class index {

    private static final Logger log = LoggerFactory.getLogger(index.class);
    @Autowired
    private UserRepo databazeTable;
    @Autowired
    private SessionRepo sesR;
    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping(value = "/question")
    public Question question() {
        Question question = new Question();

        question.Question = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/dQw4w9WgXcQ?si=igJ2zsVCZSAC6hCq\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        question.Answers.add(new Answer("Ano", true, 1));
        question.Answers.add(new Answer("Rick Rolled OMG", false, 2));
        question.Answers.add(new Answer("Haha so funny", false, 3));
        question.Answers.add(new Answer("Not funny... >:(", false, 4));

        return question;
    }

    @GetMapping(value = "/punishments")
    public Punishments punishments() {
        Punishments punishments = new Punishments();

        punishments.Name = "Test punishment";
        punishments.Id = 1L;

        return punishments;
    }

    @GetMapping(value = "/session")
    public Session session() {
        Session session = new Session();

        session.Id = 1L;
        session.Code = Generator.GenerateCode();
        session.Punisment.add(punishments());
        session.Questions.add(question());

        return session;
    }

    @GetMapping(value = "/user")
    public User user() {
        User user = new User();

        user.Score = 15000;
        user.Name = "Franta";
        user.ProfilePicture = "https://placehold.co/500";
        user.Id = UUID.randomUUID();

        return user;
    }

    @GetMapping(value = "/punishmentSet")
    public PunishmentsSets punishmentSet() {
        PunishmentsSets punishmentsSet = new PunishmentsSets();

        punishmentsSet.Punish.add(new Punishments());
        punishmentsSet.Id = 1L;

        return punishmentsSet;
    }

    @GetMapping(value = "/questionSet")
    public QuestionSets questionSet() {
        QuestionSets questionSet = new QuestionSets();

        questionSet.Id = 1L;
        questionSet.Question.add(new Question());

        return questionSet;
    }

    @GetMapping(value = "/get_users")
    public Set<User> getUsers() {
        Set<User> userList = new HashSet<>();

        userList.add(user());
        userList.add(user());
        userList.add(user());
        userList.add(user());

        return userList;
    }

    @GetMapping(value = "/get_question")
    public void GetQuestion() {

    }

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


    @PostMapping(value = "/{name}")
    public ResponseEntity<String> jmnjno(@PathVariable(name = "name") String jmenoHrace) {
        System.out.printf(jmenoHrace);

        if(Objects.equals(databazeTable.findById(4L).get().Name, jmenoHrace)) {
            return ResponseEntity.ok("Stejny kod " + jmenoHrace);
        }

        User save = new User();
        save.Name = jmenoHrace;
        try {
            databazeTable.save(save);
            return ResponseEntity.ok("User " + jmenoHrace + " saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
    }

}
