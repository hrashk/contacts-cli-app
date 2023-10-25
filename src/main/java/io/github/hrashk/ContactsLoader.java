package io.github.hrashk;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Profile("init")
public class ContactsLoader implements InitializingBean {

    private String filePath;
    private ContactsList contacts;

    public ContactsLoader() {
    }

    @Value("${app.load.path}")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Autowired
    public void setContacts(ContactsList contacts) {
        this.contacts = contacts;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Files.readAllLines(Path.of(filePath)).stream()
                .map(Contact::fromString)
                .forEach(contacts::add);
    }
}
