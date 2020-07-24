package com.example.todo.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TodoForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(groups = { TodoFinish.class, TodoDelete.class })
    private String todoId;

    @NotNull(groups = { TodoCreate.class })
    @Size(min = 1, max = 30)
    private String todoTitle;

    public interface TodoCreate {
    };

    public interface TodoFinish {
    };

    public interface TodoDelete {
    }
}