
import java.util.*;


public class TasksManager {
    protected static long id = 0;

    public HashMap<Long, Task> tasks = new HashMap<>();
    public ArrayList<Task> MemoryManager = new ArrayList<>();

    public static long idGenerator() {
        ++id;
        return id;
    }

    // добавление новой задачи EPIC , TASK
    public void putTask(Task task){
        tasks.put(task.getId(), task);
    }

    // добавление новой подзадачи
    public void putSubTask(SubTask subTask){
        long index = subTask.getEpicId();
        Epic update = (Epic) tasks.get(index);
        update.subTasksList.add(subTask);
        checkEpicStatus((Epic) tasks.get(index));
    }

    // этот метод проверяет только статусы и если нужно меняет их у Эпика.
    protected void checkEpicStatus(Epic epic) {
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
        tasks.get(task.getId()).setName(task.getName());
        tasks.get(task.getId()).setDescription(task.getDescription());
        tasks.get(task.getId()).setStatus(task.getStatus());
        tasks.get(task.getId()).setId(task.getId());
    }

    // обновление эпика
    public void updateEpic(Epic epic) {
        tasks.get(epic.getId()).setName(epic.getName());
        tasks.get(epic.getId()).setDescription(epic.getDescription());
        tasks.get(epic.getId()).setStatus(epic.getStatus());
        checkEpicStatus((Epic) tasks.get(epic.getId()));
    }

    // обновление подзадачи
    public void updateSubTask(SubTask subTask)  {
        Epic epic = (Epic) tasks.get(subTask.getEpicId());
        for (SubTask x : epic.subTasksList) {
            if (x.getId() == subTask.getId()) {
                x.setName(subTask.getName());
                x.setDescription(subTask.getDescription());
                x.setStatus(subTask.getStatus());
            }
        }
        checkEpicStatus(epic);
    }

    // получение списка всех задач
    public ArrayList<Task> showMeAllTasks() {
        ArrayList<Task> TasksForOutput = new ArrayList<>();

        for (Task x : tasks.values()) {
            if (x.getClass() == Task.class) {
                TasksForOutput.add(x);
            }
        }
        return TasksForOutput;
    }


    // получение списка всех эпиков
    public ArrayList<Task> showMeAllEpics() {
        ArrayList<Task> TasksForOutput = new ArrayList<>();
        for (Task x : tasks.values()) {
            if (x.getClass() == Epic.class) {
                TasksForOutput.add(x);
            }
        }
        return TasksForOutput;
    }

    // записываем айди в историю
    public void HistoryManager(Task task) {
        MemoryManager.add(task);
    }

    // получение задачи по айди
    public Task showMeTaskById(long id) {
        Task returnTask = null;

        if (tasks.get(id) != null) {
            returnTask = tasks.get(id);
            HistoryManager(tasks.get(id));
            return returnTask;
        } else {
            ArrayList<Task> TasksForOutput2 = showMeAllEpics();
            for (Task task : TasksForOutput2) {
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
                HistoryManager(returnTask);
                return returnTask;
            }
        }
    }

    // удаление задач по id
    public void delete(long id) {
        if (tasks.get(id) == null){
            System.out.println("Невалидный айди");
        }
        tasks.remove(id);
        MemoryManager.remove(id);
    }

    // получение списка всех подзадач у эпика
    public ArrayList<SubTask> showMeAllSubtaskInEpic(Epic epic) {
        return epic.subTasksList;
    }

    // удаление ранее добавленных задач — всех
    public void removeAll() {
        tasks.clear();
    }
}
