package io.github.hrashk;

import java.util.Objects;

public record Contact(String name, String phone, String email) {
    public static Contact fromString(String line) {
        String[] pieces = line.trim().split("\\s*;\\s*");
        return new Contact(pieces[0], pieces[1], pieces[2]);
    }

    public boolean hasSameEmailAs(Contact contact) {
        return contact != null && hasSameEmailAs(contact.email);
    }

    public boolean hasSameEmailAs(String email) {
        return Objects.equals(this.email, email);
    }
}
