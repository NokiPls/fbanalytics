package it.polimi.awt.springmvc.web;

//import it.polimi.awt.springmvc.ui.HelloWorldView;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

@Controller
public class HelloWorldController {

	@RequestMapping("/sayHelloWorld")   
	//abbiamo un controller con metodo sayHelloWorld che risponde all'URL sayHelloWorld
	
	public String sayHelloWorld(Map<String, Object> model) {
		model.put("name", "Lorenzo");
		model.put("surname", "Graziano");

		return "sayHello"; 	//pagina jsp contenuta in WEB-INF
	}
}
