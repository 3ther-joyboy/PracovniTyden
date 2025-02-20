package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.QuestionSets;
import pup.quiz.server.model.Session;

public interface QuestionSetRepo  extends CrudRepository<QuestionSets, Long> {
    @Query(value =
            "EXISTS( SELECT " +
            "* " +
            "FROM answer WHERE id = :Answer and correct = true and question = :Question)"
            , nativeQuery = true)
    Long getCorrectAnswer(@Param("Question")Long question,@Param("Answer")Long answer);

    @Query(value = "SELECT * FROM question INNER JOIN session_question ON session_question.id_question=quesiton.id WHERE question.id = :Question and id_session = :Session", nativeQuery = true)
    Session randomInSession(@Param("Session")Long session,@Param("Question")Long question);
}
