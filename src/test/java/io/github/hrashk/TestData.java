package io.github.hrashk;

public class TestData {
    public static ContactsList sampleRepo() {
        ContactsList repo = new ContactsList();
        addSampleContacts(repo);
        return repo;
    }

    public static void addSampleContacts(ContactsList repo) {
        repo.add(new Contact("Иванов Иван Иванович", "+890999999", "someEmail@example.example"));
        repo.add(new Contact("Смирнов Иван Петрович", "+890999998", "someEmail2@example.example"));
        repo.add(new Contact("Сидоров Иван Михайлович", "+890999997", "someEmail3@example.example"));
    }
}
