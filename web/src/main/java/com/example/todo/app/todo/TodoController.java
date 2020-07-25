package com.example.todo.app.todo;

import java.util.Collection;

import javax.validation.groups.Default;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("todo")
@RequiredArgsConstructor
@Slf4j
public class TodoController {
    private final TodoService todoService;

    @ModelAttribute
    public TodoForm setUpForm() {
        final TodoForm form = new TodoForm();
        return form;
    }

    /**
     * 
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(final Model model) {
        log.info("★{}", model);
        System.out.println("★" + model);

        final Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "todo/list";
    }

    /**
     * 
     * @param todoForm
     * @param bindingResult
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("create")
    public String create(@Validated({ Default.class, TodoForm.TodoCreate.class }) final TodoForm todoForm,
            final BindingResult bindingResult, final Model model, final RedirectAttributes attributes) {
        log.debug("create");

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        // TODO
        // Todo todo = beanMapper.map(todoForm, Todo.class);
        final Todo todo = new Todo();
        todo.setTodoTitle(todoForm.getTodoTitle());

        try {
            todoService.create(todo);
        } catch (final BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));
        return "redirect:/todo/list";
    }

    @PostMapping("finish")
    public String finish(@Validated({ Default.class, TodoForm.TodoFinish.class }) final TodoForm form,
            final BindingResult bindingResult, final Model model, final RedirectAttributes attributes) {
        log.debug("finish");

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            todoService.finish(form.getTodoId());
        } catch (final BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Finished successfully!")));
        return "redirect:/todo/list";
    }

    @PostMapping("delete")
    public String delete(@Validated({ Default.class, TodoForm.TodoDelete.class }) final TodoForm form,
            final BindingResult bindingResult, final Model model, final RedirectAttributes attributes) {
        log.debug("delete");

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            todoService.delete(form.getTodoId());
        } catch (final BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Deleted successfully!")));
        return "redirect:/todo/list";
    }
}