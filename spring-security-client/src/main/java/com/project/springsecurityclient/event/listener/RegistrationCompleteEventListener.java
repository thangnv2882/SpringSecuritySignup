package com.project.springsecurityclient.event.listener;

import com.project.springsecurityclient.entity.User;
import com.project.springsecurityclient.event.RegistrationCompleteEvent;
import com.project.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the Verification Token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(user, token);

        // Send Mail to User

        String url =
                event.getApplicationUrl() + "/verifyRegistration?token="
                        + token;


        //sendVericationEmail
        log.info("Click the link to verify your account : {}", url);

    }
}
