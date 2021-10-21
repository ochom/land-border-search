package com.countries.routes;

import com.countries.routes.handlers.BFS;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller
 */
@RestController
public class Controller {

    @GetMapping("/")
    public String home() {
        return "Welcome to countries route finder";
    }

    @RequestMapping(value = "routing/{origin}/{destination}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRoute(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        var resp = new BFS().getRoute(origin.toUpperCase(), destination.toUpperCase());
        if (resp == null) {
            return new ResponseEntity<>("no land crossing",HttpStatus.BAD_REQUEST);
        }
        List<String> json = Arrays.asList(resp);
        var res = "{\"route\":"+json+"}";
        
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
