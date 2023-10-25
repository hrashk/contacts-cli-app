package io.github.hrashk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactsLoaderTest {

    @Test
    public void readContacts() throws Exception {
        var repo = new ContactsList();

        var loader = new ContactsLoader();
        loader.setFilePath("src/main/resources/default-contacts.txt");
        loader.setContacts(repo);

        loader.afterPropertiesSet();

        assertEquals(3, repo.getSize());
    }
}
