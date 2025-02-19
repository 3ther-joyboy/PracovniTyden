package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Answer;
import pup.quiz.server.model.Punishments;

public interface AnswerRepo  extends CrudRepository<Answer, Long> {
}
