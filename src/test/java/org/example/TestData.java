package org.example;

public class TestData {
    public static ContactsRepo sampleRepo() {
        ContactsRepo repo = new ContactsRepo();
        repo.add(new Contact("Иванов Иван Иванович", "+890999999", "someEmail@example.example"));
        repo.add(new Contact("Смирнов Иван Петрович", "+890999998", "someEmail2@example.example"));
        repo.add(new Contact("Сидоров Иван Михайлович", "+890999997", "someEmail3@example.example"));
        return repo;
    }
}
