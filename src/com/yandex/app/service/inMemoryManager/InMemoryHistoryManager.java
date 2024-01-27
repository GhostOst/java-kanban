package com.yandex.app.service.inMemoryManager;

import com.yandex.app.model.Task;
import com.yandex.app.service.managers.HistoryManager;

import java.util.ArrayList;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyMemory = new ArrayList<>();

    @Override
    public void addMemory(Task task) {
        historyMemory.add(task);
        if (historyMemory.size() > 10) {
            historyMemory.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyMemory;
    }
}
