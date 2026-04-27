package org.example.personaltodoapp.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.personaltodoapp.Entity.Todo;
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
    public String root(HttpSession session) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }
        return "redirect:/todos";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/save-owner")
    public String saveOwner(
            @RequestParam(name = "ownerName") String ownerName,
            HttpSession session,
            Model model
    ) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            model.addAttribute("error", true);
            return "welcome";
        }

        session.setAttribute("ownerName", ownerName.trim());
        return "redirect:/todos";
    }

    @GetMapping("/todos")
    public String home(
            Model model,
            HttpSession session
    ) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }

        model.addAttribute("todos", todoService.findAll());
        return "home-list";
    }

    @GetMapping("/add")
    public String viewAdd(
            Model model,
            HttpSession session
    ) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }

        model.addAttribute("todo", new Todo());
        return "form-add-todo";
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }

        Todo todo = todoService.findById(id);

        if (todo == null) {
            return "redirect:/todos";
        }

        model.addAttribute("todo", todo);
        return "form-add-todo";
    }

    @PostMapping("/save")
    public String saveTodo(
            @Valid @ModelAttribute("todo") Todo todo,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }

        if (bindingResult.hasErrors()) {
            return "form-add-todo";
        }

        todoService.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(
            @PathVariable Long id,
            HttpSession session
    ) {
        if (session.getAttribute("ownerName") == null) {
            return "redirect:/welcome";
        }

        todoService.deleteById(id);
        return "redirect:/todos";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/welcome";
    }
}