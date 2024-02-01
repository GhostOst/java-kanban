package com.yandex.app.test;

import com.yandex.app.model.Task;
import com.yandex.app.service.managers.HistoryManager;
import com.yandex.app.service.managers.Manager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestsHistoryManager {

    private final HistoryManager historyManager = Manager.getDefaultHistory();

    //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    @Test
    void add() {
        Task task = new Task("1", "1");
        historyManager.addMemory(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
        assertEquals(new Task("1", "1"), task);
    }
}
