package com.yandex.app.service.inMemoryManager;

import com.yandex.app.model.Task;
import com.yandex.app.service.managers.HistoryManager;

import java.util.LinkedList;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {

    private static final int SIZE = 10;
    private final LinkedList<Task> historyMemory = new LinkedList<Task>();


    @Override
    public void addMemory(Task task) {
        historyMemory.add(task);
        remove();
    }

    private void remove() {
        if (historyMemory.size() > SIZE) {
            historyMemory.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(historyMemory);
    }
}
