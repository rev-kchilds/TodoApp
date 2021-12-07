package models;


import java.util.Objects;

/*
* Our models are just going to be containers for our data in the project
*   - We wont have any functionality in the models. The models will just be an object representation of our data
*
*
* Generally (not always) our models are going to look VERY similar to our database tables
* */
public class ToDo {
    private Integer id;
    private String task;
    private Boolean completed;

    public ToDo() {
    }

    public ToDo(String task) {
        this.task = task;
        this.id = 0;
        this.completed = false;

    }

    public ToDo(Integer id, String task, Boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id) && Objects.equals(task, toDo.task) && Objects.equals(completed, toDo.completed);
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                '}';
    }
}
