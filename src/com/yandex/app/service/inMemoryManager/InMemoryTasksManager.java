package com.yandex.app.service.inMemoryManager;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;
import com.yandex.app.service.managers.Manager;
import com.yandex.app.service.managers.TaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTasksManager implements TaskManager {
    public static InMemoryHistoryManager inMemoryHistoryManager = Manager.getDefaultHistory();
    private long id = 0;

    private Map<Long, Task> tasks = new HashMap<>();
    private Map<Long, SubTask> subtasks = new HashMap<>();
    private Map<Long, Epic> epics = new HashMap<>();


    private long idGenerator() {
        return id++;
    }

    public void addHistory(Task task) {
        inMemoryHistoryManager.addMemory(task);
    }


    // добавление новой задачи TASK
    @Override
    public Task putTask(Task task) {
        task.setId(idGenerator());
        tasks.put(task.getId(), task);
        return task;
    }

    // добавление новой задачи EPIC
    @Override
    public Epic putEpic(Epic epic) {
        epic.setId(idGenerator());
        epics.put(epic.getId(), epic);
        return epic;
    }

    // добавление новой подзадачи
    @Override
    public SubTask putSubTask(SubTask subTask) {
        long index = subTask.getEpicId();
        Epic update = epics.get(index);
        update.setSubTasksList(subTask);
        subTask.setId(subtasks.size());
        subtasks.put(subTask.getId(), subTask);
        updateEpicStatus(update);
        return subTask;
    }

    // этот метод проверяет только статусы и если нужно меняет их у Эпика.

    public void updateEpicStatus(Epic epic) {
        int checkNew = 0;
        int checkDone = 0;

        if (epic.getSubTasksList().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            for (SubTask subTask : epic.getSubTasksList()) {
                if (subTask.getStatus() == (Status.NEW)) {
                    checkNew++;
                } else if (subTask.getStatus() == (Status.DONE)) {
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
    @Override
    public void updateTask(Task task) {
        Task existedTask = tasks.get(task.getId());
        existedTask.setName(task.getName());
        existedTask.setDescription(task.getDescription());
        existedTask.setStatus(task.getStatus());
        existedTask.setId(task.getId());
    }

    // обновление эпика
    @Override
    public void updateEpic(Epic epic) {
        Epic existedEpic = epics.get(epic.getId());
        existedEpic.setName(epic.getName());
        existedEpic.setDescription(epic.getDescription());
        existedEpic.setStatus(epic.getStatus());
        existedEpic.setId(epic.getId());
    }

    // обновление подзадачи
    @Override
    public void updateSubTask(SubTask subTask) {
        SubTask existedSubTask = subtasks.get(subTask.getId());
        Epic epic = epics.get(subTask.getEpicId());
        existedSubTask.setName(subTask.getName());
        existedSubTask.setDescription(subTask.getDescription());
        existedSubTask.setStatus(subTask.getStatus());
        existedSubTask.setId(subTask.getId());
        updateEpicStatus(epic);
    }

    // получение списка всех задач
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // получение списка всех эпиков
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    //получение списка всех подзадач
    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subtasks.values());
    }

    // получение TASK по айди
    @Override
    public void getTaskId(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Невалидный айди");
        } else {
            System.out.println(task);
            addHistory(task);
        }
    }

    // получение SUBTASK по айди
    @Override
    public void getSubTaskId(long id) {
        SubTask subTask = subtasks.get(id);
        if (subTask == null) {
            System.out.println("Невалидный айди");
        } else {
            System.out.println(subTask);
            addHistory(subTask);
        }
    }

    // получение EPIC по айди
    @Override
    public void getEpicIdEpic(long id) {
        Epic epic = epics.get(id);
        if (epic == null) {
            System.out.println("Невалидный айди");
        } else {
            System.out.println(epic);
            addHistory(epic);
        }
    }

    // удаление TASKS по id
    @Override
    public Task deleteTask(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Невалидный айди");
        }
        task = tasks.remove(id);
        return task;
    }

    // удаление SUBTASKS по id
    @Override
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
    @Override
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
    @Override
    public ArrayList<SubTask> getAllSubtaskInEpic(Epic epic) {
        return epic.getSubTasksList();
    }

    // удаление ранее добавленных задач - TASK
    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    // удаление ранее добавленных задач - SUBTASKS
    @Override
    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasksList().clear();
            updateEpic(epic);
        }
    }

    // удаление ранее добавленных задач - EPICS
    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

}