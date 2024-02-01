package com.yandex.app.model;

public class SubTask extends Task {
    private long epicId;

    public SubTask(String task, String description, Status status, long epicId) {
        super(task, description);
        this.type = TaskType.SUBTASK;
        setStatus(status);
        setEpicId(epicId);
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
        return "com.yandex.app.model.SubTask {" +
                "name ='" + getName() +
                ", status =" + getStatus() +
                ", id =" + getId() +
                ", epic_id =" + epicId +
                ", tasktype =" + type +
                '}';
    }
}


