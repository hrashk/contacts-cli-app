package org.example;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
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
        return contacts.stream().filter(c -> Objects.equals(c.email(), email)).findAny();
    }

    public void update(Contact contact) {
        contacts.replaceAll(c -> Objects.equals(c.email(), contact.email()) ? contact : c);
    }
}
