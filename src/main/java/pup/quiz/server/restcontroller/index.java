package pup.quiz.server.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.Generator;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.Session;
import pup.quiz.server.repo.PunishmentRepo;
import pup.quiz.server.repo.SessionRepo;

import java.io.IOException;

@RestController
@RequestMapping("/servertest")
public class index {

    private static final Logger log = LoggerFactory.getLogger(index.class);
    @Autowired
    private PunishmentRepo databazeTable;
    @Autowired
    private SessionRepo sesR;

    @GetMapping(value = "/")
    public String HelloWorld() {
        return "ahoj pusinko";
    }

    @GetMapping(value = "/pocetlidi")
    public String ass(){
        Session session = new Session();
        String code = Generator.GenerateCode();
        session.Code = code;
        try {
            sesR.save(session);
        } catch (Exception error) {
            System.out.println(error.toString());
        }
        return code;
    }

    @PostMapping(value = "/{name}")
    public void jmnjno(@PathVariable(name = "name") String jmenoHrace) {
        System.out.printf(jmenoHrace);

        Punishments save = new Punishments();
        save.Name = jmenoHrace;
        databazeTable.save(save);

    }

}
