package org.leonardo.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GuestBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuestBookApplication.class, args);
    }
}
