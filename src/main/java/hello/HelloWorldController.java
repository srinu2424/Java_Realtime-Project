package hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/time")
    public String getTime() {
        return "Java_Spring_Boot_Application";
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        Greeter greeter = new Greeter();
        return greeter.sayHello();
    }
}
