package services;

import dao.ToDoDao;
import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoServiceTest {



    ToDoDao toDoDao = Mockito.mock(ToDoDao.class);

    ToDoService toDoService;

    public ToDoServiceTest(){
        this.toDoService = new ToDoService(toDoDao);
    }

    @Test
    void getAllTodosTest() {
        //arrange
        List<ToDo> todos = new ArrayList<>();
        todos.add(new ToDo(1, "sweep", false));
        todos.add(new ToDo(2, "mop", false));
        todos.add(new ToDo(3, "Listen to Kevin talk", false));
        List<ToDo> expectedValue = todos;
        Mockito.when(toDoDao.getAllToDos()).thenReturn(todos);

        //act
        List<ToDo> actualResult = toDoService.getAllTodos();

        //assert
        assertEquals(expectedValue,actualResult);
    }

    @Test
    void getOneTodoTest() {
        //arrange
        ToDo expectedResult = new ToDo(1, "sweep", false);
        Mockito.when(toDoDao.getOneToDo(expectedResult.getId())).thenReturn(expectedResult);

        //act
        ToDo actualResult = toDoService.getOneTodo(expectedResult.getId());

        //assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void createTodoGreaterThan20Test() {
        //arrange
        ToDo toDo = new ToDo(1, "Is this greater than?", false);

        //act
        Boolean actualResult = toDoService.createTodo(toDo);

        //assert
        assertFalse(actualResult);
    }

    @Test
    void createTodoLessThanOrEqualTo20Test() {
        //arrange
        ToDo toDo = new ToDo(1, "Is this less than???", false);

        //act
        Boolean actualResult = toDoService.createTodo(toDo);

        //assert
        assertTrue(actualResult);
    }


    @Test
    void completeTodoTest() {
        //arrange
        Integer toDoId = 1;

        //act
        toDoService.completeTodo(toDoId);

        //assert

        //Mockito.verify validates that a method was invoked
        //  - this is useful for void return types
        Mockito.verify(toDoDao, Mockito.times(1)).updateAToDo(toDoId);

    }

    @Test
    void deleteTodoTest() {
        //arrange
        Integer toDoId = 1;

        //act
        toDoService.deleteTodo(toDoId);

        //assert
        Mockito.verify(toDoDao, Mockito.times(1)).deleteAToDo(toDoId);
    }
}