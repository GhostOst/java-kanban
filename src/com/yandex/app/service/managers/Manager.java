package com.yandex.app.service.managers;

import com.yandex.app.service.inMemoryManager.InMemoryHistoryManager;
import com.yandex.app.service.inMemoryManager.InMemoryTasksManager;

public class Manager {

    private Manager() {
    }

    public static TaskManager getDafault() {
        return new InMemoryTasksManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
