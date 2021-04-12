package org.leonardo.guestbook.domain.guest;

import io.quarkus.agroal.DataSource;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity
@DataSource("guest-book")
public class Guest extends PanacheMongoEntity {

    private String name;
    private String email;

    public Guest() { }

    public Guest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
