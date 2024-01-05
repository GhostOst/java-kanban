public class Task {
    private long id;
    private String name;
    private String description;
    private Status status = Status.NEW;



    public Task() {
    }

    public Task(String task, Status status) {
        this.name = task;
        this.status = status;
        this.id = TasksManager.idGenerator();
    }

    public Task(String task, String description, Status status) {
        this.name = task;
        this.description = description;
        this.status = status;
        this.id = TasksManager.idGenerator();
    }

    public Task(String task, String description, Status status, long id) {
        TasksManager.idGenerator();
        this.name = task;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Task(String task, String description) {
        this.name = task;
        this.description = description;
        this.id = TasksManager.idGenerator();
    }


    public Task(String task, String description, long id) {
        this.name = task;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Task{" +
                "name ='" + name +
                ", description ='" + description +
                ", id =" + id +
                ", status ='" + status +
                '}';
    }

    @Override
    public boolean equals(Object compareObject) {
        if (this == compareObject) return true;
        if (compareObject == null) return false;
        if (getClass() != compareObject.getClass()) return false;
        Task object = (Task) compareObject;
        return id == object.id && name.equals(object.name);
    }

}
