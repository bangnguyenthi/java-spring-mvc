package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private final UserService userService;
    

    public UserController(UserService userService) {
        this.userService = userService;
       
    }

    @RequestMapping("/")
    public String getHomePages(Model model) {
        List<User> arrList=this.userService.getAllUsersByEamil("1@gmail.com");
        System.out.println(arrList);
        model.addAttribute("eric", "test");
        return "hello";
    }


    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> user=this.userService.getAllUsers();
        model.addAttribute("users1", user);
        
        return "/admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        System.out.println("check id: "+id);
        User user=this.userService.getUserByID(id);
        model.addAttribute("user",user);
        model.addAttribute("id", id);

        return "/admin/user/show";
    }
    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model,@PathVariable long id) {
        model.addAttribute("updateUser");
        model.addAttribute("newUser", new User());
        return "/admin/user/update";
    }
    
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        
        this.userService.handSaveUser(hoidanit);
        return "redirect:/admin/user";
    }
}

// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("")
// public String getHomePages() {
// return this.userService.handleHelle();

// }
// }
