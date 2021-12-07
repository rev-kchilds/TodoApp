package services;

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;

import java.util.List;


/*
* The service layer is where we do our business logic.
*
* Example: If we had a limitation on the length of the todo task, we would do that logic here
* */
public class ToDoService {
    ToDoDao toDoDao;

    public ToDoService(ToDoDao toDoDao){
        this.toDoDao = toDoDao;
    }

    public ToDoService(){
        this.toDoDao = new ToDoDaoImpl();
    }

    public List<ToDo> getAllTodos(){
        return toDoDao.getAllToDos();
    }

    public ToDo getOneTodo(Integer id){
        return toDoDao.getOneToDo(id);
    }

    //business logic example
    public Boolean createTodo(ToDo toDo){
        if(toDo.getTask().length() > 20)
            return false;

        toDoDao.createToDo(toDo);
        return true;
    }

    public void completeTodo(Integer toDoId){
        System.out.println("Todo id: " + toDoId + " has been completed");
        toDoDao.updateAToDo(toDoId);

    }

    public void deleteTodo(Integer toDoId){
        toDoDao.deleteAToDo(toDoId);
    }



}
