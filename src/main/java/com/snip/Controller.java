package com.snip;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "snip")
public class Controller {

    @RequestMapping(method = RequestMethod.GET, path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> hello (){
        return new ResponseEntity<String>("Hello Wordl!!", HttpStatus.OK);
    }
}
