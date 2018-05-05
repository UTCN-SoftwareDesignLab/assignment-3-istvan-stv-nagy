package clinic.controller.secretary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecretaryController {

    @RequestMapping(value = "/secretary", method = RequestMethod.GET)
    public String showSecretaryOperations() {
        return "secretary-page";
    }

}
