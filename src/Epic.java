import java.util.ArrayList;
import java.util.Arrays;

public class Epic extends Task {
    public ArrayList<SubTask> subTasksList;

    public Epic(String task, String description) {
        super(task, description);
        subTasksList = new ArrayList();
    }

    public Epic(String task, String description, Status status, long id) {
        super(task, description, status, id);
        subTasksList = new ArrayList();
    }

    public Epic(String task, String description, long id) {
        super(task, description, id);
        subTasksList = new ArrayList();
    }


    public Epic() {
        super();
    }

    public void setSubTasksList(ArrayList<SubTask> subTasksList) {
        this.subTasksList = subTasksList;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name ='" + getName() +
                ", description ='" + getDescription() +
                ", id =" + getId() +
                ", status =" + getStatus() +
                ", subTasksList ='" + Arrays.toString(new ArrayList[]{subTasksList}) + '\'' +
                '}';
    }
}
