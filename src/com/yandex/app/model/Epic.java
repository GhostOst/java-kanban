package com.yandex.app.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Epic extends Task {
    private final List<SubTask> subTasksList;

    public Epic(String task, String description) {
        super(task, description);
        this.type = TaskType.EPIC;
        subTasksList = new LinkedList<>();
    }

    public void setSubTasksList(SubTask subTask) {
        subTasksList.add(subTask);
    }

    public List<SubTask> getSubTasksList() {
        return subTasksList;
    }

    @Override
    public String toString() {
        return "com.yandex.app.model.Epic{" +
                "name ='" + getName() +
                ", description ='" + getDescription() +
                ", id =" + getId() +
                ", status =" + getStatus() +
                ", tasktype =" + type +
                ", subTasksList ='" + Arrays.toString(new List[]{subTasksList}) + '\'' +
                '}';
    }
}
