package com.yandex.app.test;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;
import com.yandex.app.service.managers.HistoryManager;
import com.yandex.app.service.managers.Manager;
import com.yandex.app.service.managers.TaskManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestsManagers {

    private final TaskManager taskManager = Manager.getDafault();
    private final HistoryManager historyManager = Manager.getDefaultHistory();


    @Test
    void addNewSubtask() {
        Epic epic = new Epic("Test addNewTask", "Test addNewTask description");
        final Epic savedEpic = taskManager.putEpic(epic);

        SubTask subTask = new SubTask("Test addNewTask", "Test addNewTask description", Status.NEW,0);
        final SubTask savedSubtask = taskManager.putSubTask(subTask);

        assertNotNull(subTask, "Задача не найдена.");
        assertEquals(subTask, savedSubtask, "Задачи не совпадают.");

        final ArrayList<SubTask> subTasks = taskManager.getAllSubTasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество задач.");
        assertEquals(subTask, subTasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic("Test addNewTask", "Test addNewTask description");

        final Epic savedEpic = taskManager.putEpic(epic);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final ArrayList<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }


    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");

        final Task savedTask = taskManager.putTask(task);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void add() {
        historyManager.addMemory(new Task("1", "1"));
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }


}