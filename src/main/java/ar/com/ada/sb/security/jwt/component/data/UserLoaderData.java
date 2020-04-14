package ar.com.ada.sb.security.jwt.component.data;

import ar.com.ada.sb.security.jwt.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserLoaderData {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoaderData.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Value("${spring.application.env}")
    private String appEnv;

}
