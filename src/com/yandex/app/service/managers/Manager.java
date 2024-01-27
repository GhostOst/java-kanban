package com.yandex.app.service.managers;

import com.yandex.app.service.inMemoryManager.InMemoryHistoryManager;
import com.yandex.app.service.inMemoryManager.InMemoryTasksManager;

public class Manager {
    public static InMemoryTasksManager getDafault() {
        return new InMemoryTasksManager();
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
