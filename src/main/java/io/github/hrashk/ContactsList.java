package io.github.hrashk;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ContactsList {
    private final List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    public Collection<Contact> getAll() {
        return Collections.unmodifiableList(contacts);
    }

    public int getSize() {
        return contacts.size();
    }

    public Optional<Contact> findByEmail(String email) {
        return contacts.stream().filter(c -> c.hasEmail(email)).findAny();
    }

    public void update(Contact contact) {
        contacts.replaceAll(c -> c.hasSameEmailAs(contact) ? contact : c);
    }

    public void removeByEmail(String email) {
        contacts.removeIf(c -> c.hasEmail(email));
    }
}
