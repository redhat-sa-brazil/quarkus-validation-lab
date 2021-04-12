package org.leonardo.guestbook.domain;

public class IdenticonProvisionError extends Exception {

    private static final String UNABLE_TO_MAKE_IDENTICON_MSG =
        "Unable to make an identicon using email '%s'.";

    public IdenticonProvisionError(String email, Throwable cause) {
        super(makeMessage(email), cause);
    }

    private static String makeMessage(String email) {
        return String.format(UNABLE_TO_MAKE_IDENTICON_MSG, email);
    }
}
