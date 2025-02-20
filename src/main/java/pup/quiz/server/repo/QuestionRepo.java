package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.User;

import java.util.UUID;

public interface QuestionRepo extends CrudRepository<Question, Long> {
    @Query(value = "SELECT * FROM question INNER JOIN session_question ON session_question.question_id=question.id WHERE session_question.session_id = :Session RAND( ) LIMIT 1", nativeQuery = true)
    Question randomInSession(@Param("Session") UUID session);
    @Query(value =
            "EXISTS( SELECT " +
                    "* " +
                    "FROM answer WHERE id = :Answer and correct = true and question = :Question)"
            , nativeQuery = true)
    Long getCorrectAnswer(@Param("Question")Long question, @Param("Answer")Long answer);
}
