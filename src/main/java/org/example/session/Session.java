package org.example.session;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
@Log4j2
@Accessors(chain = true, fluent = true)
public class Session {

    private boolean cashier = false;
    private boolean admin = false;

    public Session() {
        log.info("new session bean created");
    }

}
