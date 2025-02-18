package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.repo.PunishmentRepo;

@RestController
@RequestMapping("/servertest")
public class index {

    @Autowired
    private PunishmentRepo databazeTable;

    @GetMapping(value = "/")
    public String HelloWorld() {
        return "ahoj pusinko";
    }

    @GetMapping(value = "/pocetlidi")
    public String awdwa() {
        return "pocet: 5";
    }

    @PostMapping(value = "/{name}")
    public void jmnjno(@PathVariable(name = "name") String jmenoHrace) {
        System.out.printf(jmenoHrace);

        Punishments save = new Punishments();
        save.Name = jmenoHrace;
        databazeTable.save(save);
    }

}
