package org.leonardo.guestbook.shell.http.outbound;

import org.leonardo.guestbook.domain.IdenticonProvisionError;
import org.leonardo.guestbook.domain.IdenticonProvider;
import org.slf4j.Logger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class IdenticonProviderImpl implements IdenticonProvider {

    private static final String FAILED_TO_GENERATE_IDENTICON_MSG =
        "Failed to identicon for email '{}'.";

    private static final String URL_TEMPLATE =
        "https://identicon-1132.appspot.com/%s?f=base64";

    private static Logger logger = getLogger(IdenticonProviderImpl.class);

    private final RestTemplate restTemplate;

    public IdenticonProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getIdenticonFromEmail(String email)
        throws IdenticonProvisionError {

        try {
            return makeIdenticonRequest(makeHash(email));
        } catch (Throwable e) {
            logger.error(FAILED_TO_GENERATE_IDENTICON_MSG, email);
            throw new IdenticonProvisionError(email, e);
        }
    }

    private String makeHash(String email) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(email.getBytes());
        return DatatypeConverter
            .printHexBinary(digest.digest())
            .toLowerCase(Locale.ROOT);
    }

    @Retryable(
        exclude = {HttpClientErrorException.class},
        include = {HttpServerErrorException.class},
        maxAttempts = 4,
        backoff = @Backoff(delay = 2000))
    private String makeIdenticonRequest(String hash) {
        String url = UriComponentsBuilder
            .fromUriString(format(URL_TEMPLATE, hash))
            .build()
            .toUriString();
        logger.info("Trying to make a request to url '{}'.", url);
        return restTemplate.getForObject(url, String.class);
    }
}
