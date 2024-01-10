package com.yandex.app.service;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.SubTask;
import com.yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class TasksManager {
    protected long id = 0;

    public HashMap<Long, Task> tasks = new HashMap<>();
    public HashMap<Long, SubTask> subtasks = new HashMap<>();
    public HashMap<Long, Epic> epics = new HashMap<>();


    protected long idGenerator() {
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
        update.subTasksList.add(subTask);
        subTask.setId(subtasks.size());
        subtasks.put(subTask.getId(), subTask);
        updateEpicStatus(epics.get(index));
        return subTask;
    }

    // этот метод проверяет только статусы и если нужно меняет их у Эпика.
    private void updateEpicStatus(Epic epic) {
        int checkNew = 0;
        int checkDone = 0;

        if (epic.subTasksList.isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            for (SubTask y : epic.subTasksList) {
                if (y.getStatus() == (Status.NEW)) {
                    checkNew++;
                } else if (y.getStatus() == (Status.DONE)) {
                    checkDone++;
                }
            }
            if (checkDone == epic.subTasksList.size()) {
                epic.setStatus(Status.DONE);
            } else if (checkNew == epic.subTasksList.size()) {
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
        for (SubTask x : epic.subTasksList) {
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
        ArrayList<Task> TasksForOutput = new ArrayList<>();

        for (Task x : tasks.values()) {
            if (x.getClass() == Task.class) {
                TasksForOutput.add(x);
            }
        }
        return TasksForOutput;
    }


    // получение списка всех эпиков
    public ArrayList<Task> getAllEpics() {
        ArrayList<Task> tasksForOutput = new ArrayList<>();
        for (Task x : epics.values()) {
            if (x.getClass() == Epic.class) {
                tasksForOutput.add(x);
            }
        }
        return tasksForOutput;
    }

    //получение списка всех подзадач
    public HashMap<Long, SubTask> getAllSubTask() {
        return new HashMap<>(subtasks);
    }

    // получение задачи по айди
    public Task getTaskById(long id) {
        Task returnTask = null;

        if (tasks.get(id) != null) {
            returnTask = tasks.get(id);
            return returnTask;
        } else if (epics.get(id) != null) {
            returnTask = epics.get(id);
            return returnTask;
        } else {
            ArrayList<Task> tasksForOutput2 = getAllEpics();
            for (Task task : tasksForOutput2) {
                Epic x = (Epic) task;
                for (int y = 0; y < x.subTasksList.size(); y++) {
                    if (x.subTasksList.get(y).getId() == id) {
                        returnTask = x.subTasksList.get(y);
                    }
                }
            }
            if (returnTask == null) {
                System.out.println("Невалидный айди");
                return null;
            } else {
                return returnTask;
            }
        }
    }

    // получение TASK по айди
    public Task getTaskId(long id) {
        Task task;
        if (tasks.get(id) == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            task = tasks.get(id);
            return task;
        }
    }

    // получение SUBTASK по айди
    public SubTask getSubTaskId(long id) {
        SubTask subTask;
        if (subtasks.get(id) == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            subTask = subtasks.get(id);
            return subTask;
        }
    }

    // получение EPIC по айди
    public Epic getEpicIdEpic(long id) {
        Epic epic;
        if (epics.get(id) == null) {
            System.out.println("Невалидный айди");
            return null;
        } else {
            epic = epics.get(id);
            return epic;
        }
    }

    // удаление TASKS по id
    public Task deleteTasks(long id) {
        if (tasks.get(id) == null) {
            System.out.println("Невалидный айди");
        }
        tasks.remove(id);
        return null;
    }

    // удаление SUBTASKS по id
    public SubTask deleteSubtasks(long id) {
        if (subtasks.get(id) == null) {
            System.out.println("Невалидный айди");
        }
        subtasks.remove(id);
        return null;
    }

    // удаление EPICS по id
    public void deleteEpics(long id) {
        Iterator<SubTask> i = subtasks.values().iterator();
        while (i.hasNext()) {
            SubTask subTask = i.next();
            if (id == subTask.getEpicId()) {
                i.remove();
            }
        }
        if (epics.get(id) == null) {
            System.out.println("Невалидный айди");
        }
        epics.remove(id);
    }

    // получение списка всех подзадач у эпика
    public ArrayList<SubTask> getAllSubtaskInEpic(Epic epic) {
        return epic.subTasksList;
    }

    // удаление ранее добавленных задач - TASK
    public void removeAllTasks() {
        tasks.clear();
    }

    // удаление ранее добавленных задач - SUBTASKS
    public void removeAllSubtasks() {
        subtasks.clear();
    }

    // удаление ранее добавленных задач - EPICS
    public void removeAllEpics() {
        epics.clear();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TasksManager)) return false;
        TasksManager that = (TasksManager) object;
        return id == that.id && Objects.equals(tasks, that.tasks) &&
                Objects.equals(subtasks, that.subtasks) &&
                Objects.equals(epics, that.epics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tasks, subtasks, epics);
    }
}


