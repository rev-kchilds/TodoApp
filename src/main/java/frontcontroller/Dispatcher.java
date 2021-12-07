package frontcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.ToDoController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import models.ToDo;

import static io.javalin.apibuilder.ApiBuilder.*;

/*
* This is where we are going to route our endpoints to the specific methods
*
* */
public class Dispatcher {


    ToDoController toDoController = new ToDoController();

    public Dispatcher(Javalin app){
        /*
        * This is where we will route to the correct methods
        * */

        /*
        * REST
        *   - REpresenational State Transfer
        *   - Architectural standard for HTTP
        *
        * Below is what Restful endpoints look like
        * */
        app.routes(() -> {
            path("todo", () -> {
                //:: is the method reference operator
                get(toDoController::getAllTodos); // /todo GET
                post(toDoController::createTodo); // /todo POST
                path("{id}", () -> {
                    get(toDoController::getOneTodo); // /todo/3 GET   get todo with todo id that is 3
                    patch(toDoController::updateTodo); // /todo/3 PATCH
                    delete(toDoController::deleteTodo); // /todo/3 DELETE
                });
            });

        });

    }
}
