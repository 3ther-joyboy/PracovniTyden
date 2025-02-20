package pup.quiz.server.workers;


import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.repo.UserRepo;

import java.util.Collections;

public class UsersWorker {
    @Autowired
    UserRepo repo;
}
