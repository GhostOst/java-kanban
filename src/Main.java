
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Введите id задачи");
        System.out.println("2 - Показать все эпики");
        System.out.println("3 - Показать все задачи");
        System.out.println("4 - Показать все подзадачи эпика");
        System.out.println("5 - Новый статус - IN_PROGRESS");
        System.out.println("6 - Новый статус - DONE");
        System.out.println("7 - Удаление всех задач");
        System.out.println("8 - Удаление задач по id");
        System.out.println("9 - Выход");
    }

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        System.out.println("Поехали!");

        TasksManager tasksManager = new TasksManager();
        Task task1 = new Task("Задача", "Описание",
                Status.NEW,1);
        tasksManager.putTask(task1);

        Task task2 = new Task("Задача2", "Описание2",
                Status.NEW,2);
        tasksManager.putTask(task2);

        Epic epic1 = new Epic("Эпик", "Описание");
        SubTask subtask1 = new SubTask("Подзадача1", Status.NEW,
                epic1.getId(),4);
        SubTask subtask2 = new SubTask("Подзадача1", Status.NEW,
                epic1.getId(),5);
        tasksManager.putTask(epic1);
        tasksManager.putSubTask(subtask1);
        tasksManager.putSubTask(subtask2);

        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    Long id = scanner.nextLong();
                    System.out.println(tasksManager.showMeTaskById(id));
                    break;
                case "2":
                    System.out.println(tasksManager.showMeAllEpics());
                    break;
                case "3":
                    System.out.println(tasksManager.showMeAllTasks());
                    break;
                case "4":
                    System.out.println(tasksManager.showMeAllSubtaskInEpic(epic1));
                    break;
                case "5":
                    task1.setStatus(Status.IN_PROGRESS);
                    task2.setStatus(Status.IN_PROGRESS);
                    subtask1.setStatus(Status.IN_PROGRESS);
                    subtask2.setStatus(Status.IN_PROGRESS);
                    tasksManager.updateTask(task1);
                    tasksManager.updateTask(task2);
                    tasksManager.updateSubTask(subtask1);
                    tasksManager.updateSubTask(subtask2);
                    tasksManager.updateEpic(epic1);
                    tasksManager.checkEpicStatus(epic1);
                    System.out.println(epic1.getStatus());
                    break;
                case "6":
                    task1.setStatus(Status.DONE);
                    task2.setStatus(Status.DONE);
                    subtask1.setStatus(Status.DONE);
                    subtask2.setStatus(Status.DONE);
                    tasksManager.updateTask(task1);
                    tasksManager.updateTask(task2);
                    tasksManager.updateSubTask(subtask1);
                    tasksManager.updateSubTask(subtask2);
                    tasksManager.updateEpic(epic1);
                    tasksManager.checkEpicStatus(epic1);
                    System.out.println(epic1.getStatus());
                    break;
                case "7":
                    tasksManager.removeAll();
                    break;
                case "8":
                    System.out.println("Введимте номер id");
                    Long idD = scanner.nextLong();
                    tasksManager.delete(idD);
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Такой комманды не существует. Введите от 1 до 8.");
            }
        }
    }
}