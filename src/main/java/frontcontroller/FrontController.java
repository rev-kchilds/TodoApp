package frontcontroller;

import io.javalin.Javalin;

/*
* Front controller is where all endpoints hit first
*
*   - this is where our middleware is going to be
* */
public class FrontController {

    public FrontController(Javalin app){
        /*
         * middleware
         * */
        /*app.before("/protected/*",context -> {
            //check for authentication here

            System.out.println("before middleware hit");
        });

        app.after("/protected/*", context -> {
            System.out.println("after middleware hit");
        });


        //you can send specific information back for
        app.error(404, context -> {
                context.result("this is not a valid endpoint.");
        });
*/

        /*app.exception(NumberFormatException.class, (e, context) -> {
            context.result("Invalid input! Expected a number");
        });*/

        new Dispatcher(app);
    }
}
