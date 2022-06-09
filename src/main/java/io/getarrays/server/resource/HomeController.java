package io.getarrays.server.resource;

import io.getarrays.server.service.implementation.ServerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ServerServiceImplementation serverService;

    @GetMapping("/home")
    public String home(){
        return "HELLO THIS IS HOME";
    }

}
