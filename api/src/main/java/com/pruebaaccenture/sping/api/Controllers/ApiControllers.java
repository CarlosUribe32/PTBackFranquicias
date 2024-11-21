package com.pruebaaccenture.sping.api.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ApiControllers {
    
    @RequestMapping(method=RequestMethod.GET)
    public String HelloApi() {
        return "API is Working";
    }
    
    
}
