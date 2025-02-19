package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Question;
import pup.quiz.server.model.User;

public interface QuestionRepo extends CrudRepository<Question, Long> {
}
