package com.yandex.app.service;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TasksManager {
    private long id = 0;

    public HashMap<Long, Task> tasks = new HashMap<>();
    public HashMap<Long, SubTask> subtasks = new HashMap<>();
    public HashMap<Long, Epic> epics = new HashMap<>();


    private long idGenerator() {
        return id++;
    }


    // добавление новой задачи TASK
    public Task putTask(Task task) {
        task.setId(idGenerator());
        tasks.put(task.getId(), task);
        return task;
    }

    // добавление новой задачи EPIC
    public Epic putEpic(Epic epic) {
        epic.setId(idGenerator());
        epics.put(epic.getId(), epic);
        return epic;
    }

    // добавление новой подзадачи
    public SubTask putSubTask(SubTask subTask) {
        long index = subTask.getEpicId();
        Epic update = epics.get(index);
        ArrayList<SubTask> subTasks = new ArrayList<>();
        subTasks.add(subTask);
        update.setSubTasksList(subTasks);
        subTask.setId(subtasks.size());
        subtasks.put(subTask.getId(), subTask);
        updateEpicStatus(update);
        return subTask;
    }

    // этот метод проверяет только статусы и если нужно меняет их у Эпика.
    private void updateEpicStatus(Epic epic) {
        int checkNew = 0;
        int checkDone = 0;

        if (epic.getSubTasksList().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            for (SubTask y : epic.getSubTasksList()) {
                if (y.getStatus() == (Status.NEW)) {
                    checkNew++;
                } else if (y.getStatus() == (Status.DONE)) {
                    checkDone++;
                }
            }
            if (checkDone == epic.getSubTasksList().size()) {
                epic.setStatus(Status.DONE);
            } else if (checkNew == epic.getSubTasksList().size()) {
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }

    // обновление задачи
    public void updateTask(Task task) {
        Task task1 = tasks.get(task.getId());
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setStatus(task.getStatus());
        task1.setId(task.getId());
    }

    // обновление эпика
    public void updateEpic(Epic epic) {
        Epic epic1 = epics.get(epic.getId());
        epic1.setName(epic.getName());
        epic1.setDescription(epic.getDescription());
        epic1.setStatus(epic.getStatus());
    }

    // обновление подзадачи
    public void updateSubTask(SubTask subTask) {
        Epic epic = epics.get(subTask.getEpicId());
        for (SubTask x : epic.getSubTasksList()) {
            if (x.getId() == subTask.getId()) {
                x.setName(subTask.getName());
                x.setDescription(subTask.getDescription());
                x.setStatus(subTask.getStatus());
            }
        }
        updateEpicStatus(epic);
    }

    // получение списка всех задач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // получение списка всех эпиков
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    //получение списка всех подзадач
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subtasks.values());
    }

    // получение TASK по айди
    public Task getTaskId(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            return task;
        }
    }

    // получение SUBTASK по айди
    public SubTask getSubTaskId(long id) {
        SubTask subTask = subtasks.get(id);
        if (subTask == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            return subTask;
        }
    }

    // получение EPIC по айди
    public Epic getEpicIdEpic(long id) {
        Epic epic = epics.get(id);
        if (epic == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            return epic;
        }
    }

    // удаление TASKS по id
    public Task deleteTask(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Невалидный айди");
        }
        task = tasks.remove(id);
        return task;
    }

    // удаление SUBTASKS по id
    public SubTask deleteSubtask(long id) {
        SubTask subTask = subtasks.get(id);
        if (subTask == null) {
            System.out.println("Невалидный айди");
        }
        subTask = subtasks.remove(id);
        Epic epic = epics.get(subTask.getEpicId());
        epic.getSubTasksList().remove(id);
        updateEpic(epic);
        return subTask;
    }

    // удаление EPICS по id
    public Epic deleteEpic(long id) {
        Epic epic = epics.get(id);
        if (epic == null) {
            System.out.println("Невалидный айди");
        }
        epic = epics.remove(id);
        subtasks.entrySet().removeIf(entry -> entry.getValue().getEpicId() == id);
        return epic;
    }

    // получение списка всех подзадач у эпика
    public ArrayList<SubTask> getAllSubtaskInEpic(Epic epic) {
        return epic.getSubTasksList();
    }

    // удаление ранее добавленных задач - TASK
    public void removeAllTasks() {
        tasks.clear();
    }

    // удаление ранее добавленных задач - SUBTASKS
    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasksList().clear();
            updateEpic(epic);
        }
    }

    // удаление ранее добавленных задач - EPICS
    public void removeAllEpics() {
        epics.clear();
        removeAllSubtasks();
    }

}


