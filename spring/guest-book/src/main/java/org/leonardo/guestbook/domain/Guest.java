package org.leonardo.guestbook.domain;

import io.quarkus.agroal.DataSource;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guest {
    @Id
    private String id;
    private String name;
    private String email;
    private String identicon;

    public Guest() { }

    public Guest(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identicon = null;
    }

    public Guest(String id, String name, String email, String identicon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identicon = identicon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIdenticon() {
        return identicon;
    }

    public void setIdenticon(String identicon) {
        this.identicon = identicon;
    }

    public Guest updateIdenticon(IdenticonProvider identiconProvider)
        throws IdenticonProvisionError {
        identicon = identiconProvider.getIdenticonFromEmail(email);
        return this;
    }
}
