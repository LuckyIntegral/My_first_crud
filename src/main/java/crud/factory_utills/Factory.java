package crud.factory_utills;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Factory {
    private String name;
    private String country;
    private String id;
    private static final String DEFAULT_VALUE = "Unspecified";
    public static final String FIELDS_NAMES = "Name        Country             id";
    private static final Set<String> passwords = new HashSet<>();

    @Override
    public String toString() {
        return name + " ".repeat(12 - name.length()) +
                country + " ".repeat(20 - country.length()) + getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 12) {
            System.out.println("This name is too long");
            name = DEFAULT_VALUE;
        } else {
            this.name = name.trim();
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.matches("\\W+")) {
            System.out.println("This country name is incorrect");
            country = DEFAULT_VALUE;
        } else if (country.length() > 20) {
            System.out.println("This country name is too long");
            country = DEFAULT_VALUE;
        } else {
            this.country = country;
        }
    }

    public String getId() {
        if (this.id == null) {
            String temp = UUID.randomUUID().toString().substring(0, 8);
            while (passwords.contains(temp)) {
                temp = UUID.randomUUID().toString().substring(0, 8);
            }
            passwords.add(temp);
            id = temp;
        }
        return id;
    }
}
