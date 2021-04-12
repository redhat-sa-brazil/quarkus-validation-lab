package org.leonardo.guestbook.domain;

public interface IdenticonProvider {
    String getIdenticonFromEmail(String email) throws IdenticonProvisionError;
}
