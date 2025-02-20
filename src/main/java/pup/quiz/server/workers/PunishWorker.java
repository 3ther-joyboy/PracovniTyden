package pup.quiz.server.workers;

import org.springframework.beans.factory.annotation.Autowired;
import pup.quiz.server.model.PunishmentsSets;
import pup.quiz.server.repo.PunismentSetRepo;

public class PunishWorker {
    @Autowired
    private PunismentSetRepo p_rep;

    public Long CreateSet(String name) {
        PunishmentsSets set = new PunishmentsSets();
        set.Name = name;
        return p_rep.save(set).Id;
    }
}
