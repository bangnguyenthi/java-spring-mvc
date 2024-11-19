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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




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



    @RequestMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model,@PathVariable long id) {
        User currentUser=this.userService.getUserByID(id);

        model.addAttribute("newUser", currentUser);
        return "/admin/user/update";
    }

    
    @PostMapping("/admin/user/update")

    public String postUserUpdate(Model model,@ModelAttribute("newUser") User hoidanit) {
        User currentUser=this.userService.getUserByID(hoidanit.getId());
        if(currentUser != null){
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());

            this.userService.handSaveUser(currentUser);

        }
       
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getUserDeleteUserPage(Model model,@PathVariable long id) {
        model.addAttribute("id", id);
        User user=new User();
        user.setId(id);
        model.addAttribute("newUser", user);

        return "/admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postUserDeleteUser(Model model,@ModelAttribute("newUser") User user) {
        this.userService.deleteUser(user.getId());
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
