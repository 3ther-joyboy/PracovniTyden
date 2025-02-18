package pup.quiz.server.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.Session;
import pup.quiz.server.repo.PunishmentRepo;
import pup.quiz.server.repo.SessionRepo;

@RestController
@RequestMapping("/servertest")
public class index {

    @Autowired
    private PunishmentRepo databazeTable;
    @Autowired
    private SessionRepo sesR;

    @GetMapping(value = "/")
    public String HelloWorld() {
        return "ahoj pusinko";
    }

    @GetMapping(value = "/pocetlidi")
    public String awdwa() {
        return "pocet: 5";
    }

    @GetMapping(value = "/code/generate")
    public Session as(){
        Session asdf = new Session();
        asdf.Code = asdf.GenerateCode();
        sesR.save(asdf);
        return asdf;

    }
    @PostMapping(value = "/{name}")
    public void jmnjno(@PathVariable(name = "name") String jmenoHrace) {
        System.out.printf(jmenoHrace);

        Punishments save = new Punishments();
        save.Name = jmenoHrace;
        databazeTable.save(save);

    }

}
