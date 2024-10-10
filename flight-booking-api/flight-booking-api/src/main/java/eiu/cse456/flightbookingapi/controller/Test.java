package eiu.cse456.flightbookingapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public String Test(){
        return "Hello, this is test" ;
    }
}
