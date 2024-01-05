public class SubTask extends Task {
    private long epicId;

    public SubTask() {
    }

    public SubTask(String task, Status status, long Epicid) {
        super(task, status);
        setStatus(status);
        setEpicId(Epicid);
    }

    public SubTask(String task, Status status, long Epicid, long id) {
        super(task, status);
        setStatus(status);
        setEpicId(Epicid);
        setId(id);
    }

    public void setEpicId(long epicid) {
        this.epicId = epicid;
    }

    public long getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask {" +
                "name ='" + getName() +
                ", status =" + getStatus() +
                ", id =" + getId() +
                ", epic_id =" + epicId +
                '}';
    }
}


