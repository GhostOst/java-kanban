package com.yandex.app.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Epic extends Task {
    private ArrayList<SubTask> subTasksList;

    public Epic(String task, String description) {
        super(task, description);
        this.type = TaskType.EPIC;
        subTasksList = new ArrayList<>();
    }

    public void setSubTasksList(ArrayList<SubTask> subTasksList) {
        this.subTasksList = subTasksList;
    }

    public ArrayList<SubTask> getSubTasksList() {
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
                ", subTasksList ='" + Arrays.toString(new ArrayList[]{subTasksList}) + '\'' +
                '}';
    }
}
