package pup.quiz.server.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servertest")
public class index {
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
        //adw
    }
}
