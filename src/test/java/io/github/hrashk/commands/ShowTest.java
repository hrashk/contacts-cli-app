package io.github.hrashk.commands;

import io.github.hrashk.ContactsList;
import io.github.hrashk.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowTest {

    @Test
    void handleEmptyRepo() {
        var s = new Show(new ContactsList());
        String respnose = s.handle("show");
        assertEquals(Show.NO_CONTACTS, respnose);
    }

    @Test
    void handleNonEmptyRepo() {
        var s = new Show(TestData.sampleRepo());
        String respnose = s.handle("show");
        assertEquals("""
                Иванов Иван Иванович | +890999999 | someEmail@example.example
                Смирнов Иван Петрович | +890999998 | someEmail2@example.example
                Сидоров Иван Михайлович | +890999997 | someEmail3@example.example""", respnose);
    }
}
