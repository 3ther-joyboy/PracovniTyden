package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.User;

public interface QuestionRepo extends CrudRepository<Question, Long> {

    @Query(value =
            "EXISTS( SELECT " +
                    "* " +
                    "FROM answer WHERE id = :Answer and correct = true and question = :Question)"
            , nativeQuery = true)
    Long getCorrectAnswer(@Param("Question")Long question, @Param("Answer")Long answer);
}
