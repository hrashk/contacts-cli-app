package io.github.hrashk;

public record Contact(String name, String phone, String email) {
    public static Contact fromString(String line) {
        String[] pieces = line.trim().split("\\s*;\\s*");
        return new Contact(pieces[0], pieces[1], pieces[2]);
    }
}
