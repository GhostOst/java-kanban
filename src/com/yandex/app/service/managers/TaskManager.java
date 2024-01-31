package com.yandex.app.service.managers;

import com.yandex.app.model.Epic;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;

import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    void addHistory(Task task);

    // добавление новой задачи TASK
    Task putTask(Task task);

    // добавление новой задачи EPIC
    Epic putEpic(Epic epic);

    // добавление новой подзадачи
    SubTask putSubTask(SubTask subTask);

    // обновление задачи
    void updateTask(Task task);

    // обновление эпика
    void updateEpic(Epic epic);

    // обновление подзадачи
    void updateSubTask(SubTask subTask);

    // получение списка всех задач
    List<Task> getAllTasks();

    // получение списка всех эпиков
    List<Epic> getAllEpics();

    //получение списка всех подзадач
    List<SubTask> getAllSubTasks();

    // получение TASK по айди
    void getTaskId(long id);

    // получение SUBTASK по айди
    void getSubTaskId(long id);

    // получение EPIC по айди
    void getEpicIdEpic(long id);

    // удаление TASKS по id
    Task deleteTask(long id);

    // удаление SUBTASKS по id
    SubTask deleteSubtask(long id);

    // удаление EPICS по id
    Epic deleteEpic(long id);

    // получение списка всех подзадач у эпика
    List<SubTask> getAllSubtaskInEpic(long epic);

    // удаление ранее добавленных задач - TASK
    void removeAllTasks();

    // удаление ранее добавленных задач - SUBTASKS
    void removeAllSubtasks();

    // удаление ранее добавленных задач - EPICS
    void removeAllEpics();
}
