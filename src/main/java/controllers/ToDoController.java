package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpl;
import io.javalin.http.Context;
import models.ToDo;
import services.ToDoService;

import java.util.List;

/*
* The controllers is where we will implement our http request and response methods
* */
public class ToDoController {

    static ToDoService toDoService = new ToDoService();

    public void getAllTodos(Context context) throws JsonProcessingException {
        context.contentType("application/json"); //sending back json

        List<ToDo> toDoList = toDoService.getAllTodos(); //get all todos from db

        /*
         * What is json?
         *   JSON stands for JavaScript Object Notation
         *   - json is just a universal format for storing and transporting data
         *   - json stores data in key value pairs
         *
         * below is an example of json format
         * {
         *    "id": 1,
         *    "task": "this is my task!",
         *    "completed": false
         * }
         * */
        String jsonString = new ObjectMapper().writeValueAsString(toDoList); //we used jackson to convert our list object to a json string

        context.result(jsonString); //send data back
    }


    public void getOneTodo(Context context) throws JsonProcessingException {
        context.contentType("application/json"); //sending back json
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        ToDo todo = toDoService.getOneTodo(todoId);

        context.result(new ObjectMapper().writeValueAsString(todo));
    }

    public void createTodo(Context context) {
        ToDo todo = context.bodyAsClass(ToDo.class);

        if(toDoService.createTodo(todo)){
            context.result("todo created");
        }else{
            context.result("task was too long... make the task less than 20 characters");
        }
    }

    public void updateTodo(Context context) {
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.completeTodo(todoId);

        context.result("Todo with id " + todoId + " has been completed");
    }

    public void deleteTodo(Context context) {
        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.deleteTodo(todoId);

        context.result("Deleted todo with id " + todoId + " if it exists");
    }
}
