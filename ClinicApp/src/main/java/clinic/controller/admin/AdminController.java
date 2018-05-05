package clinic.controller.admin;



import clinic.controller.ErrorExtractor;
import clinic.dto.UserDTO;
import clinic.entity.User;
import clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAdminPage() {
        return "admin-page";
    }

    @RequestMapping(value = "/findall")
    public String findAllUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user-list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") @Valid UserDTO userDto, BindingResult result) {
        if (!result.hasErrors()) {
            userService.create(userDto);
            return "redirect:/admin";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            return "user-form";
        }
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable(value="userId") Integer id) {
        userService.delete(id);
        return "redirect:/admin/findall";
    }

    @RequestMapping(value="/update/{userId}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value="userId") Integer id) {
        User user = userService.findById(id);
        UserDTO userDto = new UserDTO(user.getUsername(), user.getPassword(), user.getRole());
        userDto.setId(id);
        model.addAttribute("user", userDto);
        return "user-update-form";
    }

    @RequestMapping(value="/update/{userId}", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") @Valid UserDTO userDto, BindingResult result, @PathVariable(value="userId") Integer id) {
        if (!result.hasErrors()) {
            String encodedPassword = new ShaPasswordEncoder().encodePassword(userDto.password, "SHA-1");
            userDto.setPassword(encodedPassword);
            userService.update(id, userDto);
            return "redirect:/admin/findall";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            UserDTO newUserDto = new UserDTO();
            userDto.setId(id);
            model.addAttribute(newUserDto);
            return "user-update-form";
        }
    }

}

