package com.yandex.app;

import com.yandex.app.model.*;

import com.yandex.app.service.TasksManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        TasksManager tasksManager = new TasksManager();
        TasksManager tasksManager1 = new TasksManager();
        Task task1 = tasksManager.putTask(new Task("1", "One"));
        System.out.println(task1);
        Task task2 = tasksManager.putTask(new Task("2", "Two"));
        System.out.println(task2);
        Epic epic1 = tasksManager1.putEpic(new Epic("Эпик1", "Описание1"));
        System.out.println(epic1);
        SubTask subtask1 = tasksManager1.putSubTask(new SubTask("Подзадача1", "OneS", Status.NEW, 0));
        System.out.println(subtask1);
        SubTask subtask2 = tasksManager1.putSubTask(new SubTask("Подзадача2", "TwoS", Status.NEW, 0));
        System.out.println(subtask2);
        Epic epic2 = tasksManager1.putEpic(new Epic("Эпик2", "Описание2"));
        System.out.println(epic2);
        SubTask subtask3 = tasksManager1.putSubTask(new SubTask("Подзадача2-2", "TwoS2", Status.NEW, 1));
        System.out.println(subtask3);
        System.out.println(tasksManager.getAllTasks());
        System.out.println(tasksManager1.getAllEpics());
        System.out.println(tasksManager1.getAllSubTasks());
        task1.setStatus(Status.DONE);
        tasksManager.updateTask(task1);
        task2.setStatus(Status.DONE);
        tasksManager.updateTask(task2);
        subtask1.setStatus(Status.DONE);
        tasksManager1.updateSubTask(subtask1);
        subtask2.setStatus(Status.DONE);
        tasksManager1.updateSubTask(subtask2);
        tasksManager1.updateEpic(epic1);
        System.out.println(tasksManager.getAllTasks());
        System.out.println(tasksManager1.getAllEpics());
        System.out.println(tasksManager1.getAllSubTasks());
        System.out.println(tasksManager.getTaskId(1));
        System.out.println(tasksManager1.getEpicIdEpic(1));
        System.out.println(tasksManager1.getSubTaskId(2));
        tasksManager.deleteTask(1);
        tasksManager1.deleteEpic(0);
        System.out.println(tasksManager.getAllTasks());
        System.out.println(tasksManager1.getAllEpics());
        System.out.println(tasksManager1.getAllSubTasks());
    }
}