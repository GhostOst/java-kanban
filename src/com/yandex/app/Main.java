package com.yandex.app;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;
import com.yandex.app.service.managers.Manager;
import com.yandex.app.service.managers.TaskManager;

//import com.yandex.app.service.TasksManager;

public class Main {

    public static void main(String[] args) {
        /*System.out.println("Поехали!");
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
        System.out.println(tasksManager1.getAllSubTasks());*/

        /*
        inMemoryTasksManager1.getTaskId(10);
        inMemoryTasksManager.getEpicIdEpic(10);
        inMemoryTasksManager.getEpicIdEpic(10);
        inMemoryTasksManager1.getTaskId(10);
        inMemoryTasksManager.getEpicIdEpic(0);
        inMemoryTasksManager.getEpicIdEpic(1);
        inMemoryTasksManager1.getTaskId(0);
        inMemoryTasksManager.getEpicIdEpic(0);
        inMemoryTasksManager.getEpicIdEpic(1);
        inMemoryTasksManager1.getTaskId(0);
        inMemoryTasksManager.getEpicIdEpic(0);
        inMemoryTasksManager.getEpicIdEpic(0);
        System.out.println(inMemoryTasksManager1.getHistory().size());*/
        TaskManager inMemoryTasksManager = Manager.getDafault();
        TaskManager inMemoryTasksManager1 = Manager.getDafault();
        Task task1 = inMemoryTasksManager1.putTask(new Task("1", "One"));
        Epic epic = inMemoryTasksManager.putEpic(new Epic("1", "1"));
        SubTask subTask2 = inMemoryTasksManager.putSubTask(new SubTask("!", "1", Status.NEW, 0));
        for (int i = 0; i < 6; i++) {
            inMemoryTasksManager.getEpicIdEpic(0);
            System.out.println("История" + inMemoryTasksManager.getHistory());
            System.out.println(inMemoryTasksManager.getHistory().size());
            inMemoryTasksManager.getSubTaskId(1);
            System.out.println("История" + inMemoryTasksManager.getHistory());
            System.out.println(inMemoryTasksManager.getHistory().size());
        }
        printAllTasks(inMemoryTasksManager);
        System.out.println(inMemoryTasksManager.getHistory().size());
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getAllSubtaskInEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}