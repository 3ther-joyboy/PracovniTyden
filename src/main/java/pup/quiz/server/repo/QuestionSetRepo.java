package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.QuestionSets;
import pup.quiz.server.model.Session;

public interface QuestionSetRepo  extends CrudRepository<QuestionSets, Long> {
}
