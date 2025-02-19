package pup.quiz.server.restcontroller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pup.quiz.server.Generator;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.Session;
import pup.quiz.server.repo.PunishmentRepo;
import pup.quiz.server.repo.SessionRepo;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/servertest")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<String> jmnjno(@PathVariable(name = "name") String jmenoHrace) {
        System.out.printf(jmenoHrace);

        if(Objects.equals(databazeTable.findById(4L).get().Name, jmenoHrace)) {
            return ResponseEntity.ok("Stejny kod " + jmenoHrace);
        }

        Punishments save = new Punishments();
        save.Name = jmenoHrace;
        try {
            databazeTable.save(save);
            return ResponseEntity.ok("User " + jmenoHrace + " saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
    }

}
