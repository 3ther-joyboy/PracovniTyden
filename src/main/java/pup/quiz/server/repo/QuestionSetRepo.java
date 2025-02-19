package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.QuestionSets;

public interface QuestionSetRepo  extends CrudRepository<QuestionSets, Long> {
}
