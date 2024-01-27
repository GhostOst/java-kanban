package com.yandex.app.model;

import java.util.Objects;

public class Task {
    protected long id;
    protected String name;
    protected String description;
    protected TaskType type = TaskType.TASK;
    protected Status status = Status.NEW;


    public Task(String task, String description) {
        this.name = task;
        this.description = description;
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

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "com.yandex.app.model.Task{" +
                "name ='" + name +
                ", description ='" + description +
                ", id =" + id +
                ", status ='" + status +
                ", tasktype =" + type +
                '}';
    }

    @Override
    public boolean equals(Object compareObject) {
        if (this == compareObject) return true;
        if (compareObject == null) return false;
        if (getClass() != compareObject.getClass()) return true;
        Task object = (Task) compareObject;
        return id == object.id && name.equals(object.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
