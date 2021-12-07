package dao;

import models.ToDo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoImpl implements ToDoDao {
    /*
     * What is a DAO?
     *   Data Access Object
     *   - The dao isolates the persistence layer from application logic
     *   - We will (generally) have a DAO for each model
     *
     * What is JDBC?
     *   Java Database Connectivity
     *
     * JDBC is a technology that allows java to connect to a database
     *   - JDBC is modular. It doesnt just connect to one specific database... it can connect depending on database driver
     *
     * What is needed to connect to a database using JDBC?
     *   - database URL
     *   - db username
     *   - db password
     *   - specific driver for you dbms
     *
     * Important classes and interfaces that utilize JDBC:
     *   - DriverManager (class)
     *       class for managing JDBC drivers
     *       NOTE: DriverManager.getConnection() will generate our connect object
     *   - Connection (interface)
     *       - active connection with our database
     *   - Statement (interface)
     *       object that represents our SQL statement
     *       - The Statement interface itself does not protect against SQL injection
     *       - PreparedStatement (interface)
     *           - protects against SQL injection (this is what we will be using)
     *       - CallableStatement (interface)
     *           - used for stored procedures
     *   - ResultSet (interface)
     *       object that will have our results
     *
     *
     * The most common error i see...
     *  "No suitable driver found"
     *      - your url has a typo
     *      - you forgot to add the dependency
     * */


    String url;
    String username;
    String password;

    Logger logger = Logger.getLogger(ToDoDaoImpl.class);

    public ToDoDaoImpl(){
        /*jdbc:postgresql://[your endpoint here]/[the specific database you want to connect to]*/
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/tododatabase";
        this.username =  System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ToDoDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();

        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password)){ //try with resources

            //sql that we will be executing
            String sql = "SELECT * FROM todos;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the sql statement and return the result set
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()){
                todos.add(new ToDo(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
            }

        }catch (SQLException e){
            logger.error(e);
        }

        return todos;
    }

    @Override
    public ToDo getOneToDo(Integer toDoId) {
        ToDo toDo = null;

        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password)){ //try with resources

            //sql that we will be executing
            String sql = "SELECT * FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //we are setting the value of the question
            ps.setInt(1,toDoId);

            //execute the sql statement and return the result set
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()) {
                toDo = new ToDo(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }

        }catch (SQLException e){
            logger.error(e);
        }

        return toDo;
    }

    @Override
    public void createToDo(ToDo toDo) {

        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password)){ //try with resources

            //sql that we will be executing
            String sql = "INSERT INTO todos VALUES (DEFAULT, ?, DEFAULT);";
            PreparedStatement ps = conn.prepareStatement(sql);

            //set value of question mark. the parameter index is which question mark you want to assign
            ps.setString(1, toDo.getTask());

            //execute the update
            ps.executeUpdate();

        }catch (SQLException e){
            logger.error(e);
        }

    }

    @Override
    public void updateAToDo(Integer toDoId) {
        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password)){ //try with resources

            //sql that we will be executing
            String sql = "UPDATE todos SET completed = TRUE WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //set value of question mark. the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();

        }catch (SQLException e){
            logger.error(e);
        }
    }

    @Override
    public void deleteAToDo(Integer toDoId) {
        //creates active connection to the database
        try(Connection conn = DriverManager.getConnection(url, username, password)){ //try with resources

            //sql that we will be executing
            String sql = "DELETE FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //set value of question mark. the parameter index is which question mark you want to assign
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();

        }catch (SQLException e){
            logger.error(e);
        }
    }




}
