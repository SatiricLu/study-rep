package com.satiric.doit.Controllers;

import com.satiric.doit.Tables.*;
import com.satiric.doit.TablesForm.LoginForm;
import com.satiric.doit.TablesForm.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Controller
public class WebController {
    private boolean login=false;
    private String username="";
    @Autowired
    private taskRepository taskRepo;
    @Autowired
    private categoriesRepository categoryRepo;
    @Autowired
    private userRepository userRepo;
    @Autowired
    private plannersRepository plannerRepo;
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        if (!login){
            model.addAttribute("loginForm",new LoginForm());
            return "sign-in";
        };
        model.addAttribute("username",username);
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        Iterable<Planner> planners = plannerRepo.findAllByUser(username);
        model.addAttribute("planners", planners);
        return "index";
    }
    @RequestMapping(value = {"/index" }, method = RequestMethod.POST)
    public String setCategory(Model model){
        return "index";
    };
    @RequestMapping(value = { "/sign-in" }, method = RequestMethod.GET)
    public String signGet(@ModelAttribute("loginForm") LoginForm loginForm,Model model) {
        this.login=false;
        return "sign-in";
    }
    @RequestMapping(value = { "/sign-in" }, method = RequestMethod.POST)
    public String signSubmit(@ModelAttribute("loginForm") LoginForm loginForm,Model model) {
        String login = loginForm.getLogin();
        String pass = loginForm.getPass();
        User user = userRepo.findByLogin(login);
        if(user !=null) {
            if(user.getPass().equals(pass)) {
                this.login=true;
                this.username=login;
                return "redirect:/";
            }
        }
        model.addAttribute("errorMessage","Неправильный логин/пароль!");
        return "sign-in";
    }
    @RequestMapping(value = { "/category"}, method = RequestMethod.GET)
    public String categoryGet(Model model) {
        Category newCategory = new Category();
        model.addAttribute("category", newCategory);
        model.addAttribute("username", username);
        return "category";
    }
    @RequestMapping(value = { "/category"}, method = RequestMethod.POST)
    public String categoryAdd(@ModelAttribute("category") Category newCategory,Model model) {
        categoryRepo.save(newCategory);
        return "redirect:/";
    }

    @RequestMapping(value = { "/task"}, method = RequestMethod.GET)
    public String taskGet(Model model) {
        Iterable<Task> tasks=taskRepo.findAll();
        Planner newPlanner = new Planner();
        model.addAttribute("plannerForm", newPlanner);
        model.addAttribute("username", username);
        model.addAttribute("tasks", tasks);
        return "task";
    }
    @RequestMapping(value = { "/task"}, method = RequestMethod.POST)
    public String taskAdd(@ModelAttribute("plannerForm") Planner newPlanner,Model model) {
        Task task=taskRepo.findByName(newPlanner.getTask());
        newPlanner.setName(username);
        newPlanner.setCategory(task.getCategory());
        newPlanner.setStatus("активен");
        plannerRepo.save(newPlanner);
        return "redirect:/";
    }
    @RequestMapping(value = { "/addTask"}, method = RequestMethod.GET)
    public String tasGet(Model model) {
        Iterable<Category> categories=categoryRepo.findAll();
        Task newTask = new Task();
        model.addAttribute("taskForm", newTask);
        model.addAttribute("username", username);
        model.addAttribute("categories", categories);
        return "addTask";
    }
    @RequestMapping(value = { "/addTask"}, method = RequestMethod.POST)
    public String tasAdd(@ModelAttribute("taskForm") Task newTask,Model model) {
        taskRepo.save(newTask);
        return "redirect:/";
    }
    @RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
    public String regGet(Model model) {
        UserForm users = new UserForm();
        model.addAttribute("users",users);
        return "registration";
    }
    @RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
    public String regSubmit(@ModelAttribute("users") UserForm users,Model model) {
        if(userRepo.existsUserByLogin(users.getLogin())){
            model.addAttribute("errorMessage","Такой логин уже занят!");
            return "registration";
        }
        User newUser = new User();
        newUser.setFirst_name(users.getFirstName());
        newUser.setSecond_name(users.getSecondName());
        newUser.setLogin(users.getLogin());
        newUser.setPass(users.getPass());
        Date date = new Date();
        Timestamp times=new Timestamp(date.getTime());
        newUser.setDate_create(times);
        userRepo.save(newUser);
        return "redirect:/";
    }
}
