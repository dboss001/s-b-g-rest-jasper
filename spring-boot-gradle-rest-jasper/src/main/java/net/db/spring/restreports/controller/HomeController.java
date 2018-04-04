package net.db.spring.restreports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {

        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {

        return "index";
    }
    
    @RequestMapping(value = "/swagger", method = RequestMethod.GET)
    public String swagger() {

        return "swagger";
    }
	
}
