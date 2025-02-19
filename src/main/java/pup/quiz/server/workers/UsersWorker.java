package pup.quiz.server.workers;


import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.repo.UserRepo;

import java.util.Collections;

public class UsersWorker {
    @Autowired
    static UserRepo repo;

    public static void Test(long id) {
        repo.deleteAllById(Collections.singleton(id));
    }
}
