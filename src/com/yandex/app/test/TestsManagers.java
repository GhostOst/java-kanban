package com.yandex.app.test;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;
import com.yandex.app.service.managers.Manager;
import com.yandex.app.service.managers.TaskManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestsManagers {

    private final TaskManager taskManager = Manager.getDafault();

    //убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;
    @Test
    void newManager() {
        assertNotNull(Manager.getDafault(), "Не возвращает");
        assertNotNull(Manager.getDefaultHistory(), "Не возвращает");
    }

    @Test
    void addNewSubtask() {
        Epic epic = new Epic("Test addNewTask", "Test addNewTask description");
        final Epic savedEpic = taskManager.putEpic(epic);

        SubTask subTask = new SubTask("Test addNewTask", "Test addNewTask description", Status.NEW, 0);
        SubTask subTask1 = new SubTask("Test", "Test", Status.NEW, 0);
        final SubTask savedSubtask = taskManager.putSubTask(subTask);
        final SubTask savedSubtask1 = taskManager.putSubTask(subTask1);
        savedSubtask1.setId(0);
        savedSubtask.setId(0);

        assertNotNull(subTask, "Задача не найдена.");
        assertEquals(subTask, savedSubtask, "Задачи не совпадают.");

        final List<SubTask> subTasks = taskManager.getAllSubTasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(2, subTasks.size(), "Неверное количество задач.");
        assertEquals(subTask, subTasks.get(0), "Задачи не совпадают.");

        //проверьте, что наследники класса Task равны друг другу, если равен их id
        assertEquals(savedSubtask1, savedSubtask, "Не равны");

        //проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;
        assertThrows(
                NullPointerException.class,
                () -> {
                    taskManager.putSubTask(new SubTask("1", "1", Status.NEW, 1));
                }
                , "-");

        //проверьте, что объект Subtask нельзя сделать своим же эпиком;
        assertEquals(savedSubtask, subTasks.get(0), "Нет в хранилище");
        assertEquals(savedSubtask.getEpicId(), savedEpic.getId(), "Нет в хранилище");
    }

    @Test
    void addNewEpic() {
        Epic epic = new Epic("Test addNewTask", "Test addNewTask description");
        Epic epic1 = new Epic("1", "T1");
        final Epic savedEpic = taskManager.putEpic(epic);
        savedEpic.setId(0);
        final Epic savedEpic1 = taskManager.putEpic(epic1);
        savedEpic1.setId(0);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getAllEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(2, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");

        //проверьте, что наследники класса Task равны друг другу, если равен их id
        assertEquals(epic, epic1, "Не равны");
    }


    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        Task task1 = new Task("t", "d");

        final Task savedTask1 = taskManager.putTask(task1);
        savedTask1.setId(0);

        final Task savedTask = taskManager.putTask(task);
        savedTask.setId(0);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");

        //проверьте, что экземпляры класса Task равны друг другу, если равен их id;
        assertEquals(task, task1, "Не равны");
    }

    //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    @Test
    void add() {
        Task task = new Task("1", "1");
        taskManager.addHistory(task);
        final List<Task> history = taskManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
        assertEquals(new Task("1", "1"), task);
    }


}