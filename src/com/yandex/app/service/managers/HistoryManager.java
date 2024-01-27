package com.yandex.app.service.managers;

import com.yandex.app.model.Task;

import java.util.List;

public interface HistoryManager {
    void addMemory(Task task);

    List<Task> getHistory();
}
