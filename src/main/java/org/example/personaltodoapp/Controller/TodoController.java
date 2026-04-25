package org.example.personaltodoapp.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.personaltodoapp.Entity.Todo;
import org.example.personaltodoapp.repository.ITodoRepository;
import org.example.personaltodoapp.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService todoService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("todos", todoService.findAll());
        return "home-list";
    }

    @GetMapping("/add")
    public String viewAdd(Model model){
        model.addAttribute("todo", new Todo());
        return "form-add-todo";
    }

    @PostMapping("/add")
    public String addTodo(
            @Valid @ModelAttribute("todo") Todo todo,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "form-add-todo";
        }

        todoService.save(todo);
        return "redirect:/";
    }

}
