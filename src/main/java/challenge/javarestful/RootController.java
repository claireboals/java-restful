package challenge.javarestful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, Syndic8!";
	}

}