package pup.quiz.server.workers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import pup.quiz.server.repo.UserRepo;

import java.util.Collections;

@Service
@Configurable
public class UsersWorker {
    @Autowired
    UserRepo repo;
}
