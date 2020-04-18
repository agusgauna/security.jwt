package ar.com.ada.sb.security.jwt.component.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component @Order(1)
public class DataAuthorityLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataAuthorityLoader.class);

    @Value("${spring.application, env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataRoleLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Authority");

        }
    }
}
