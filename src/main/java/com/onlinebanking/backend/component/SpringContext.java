package com.onlinebanking.backend.component;

import com.onlinebanking.shared.util.ApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * A convenient class to retrieve registered beans from the application context.
 *
 * @author Eric Opoku
 * @version 1.0
 * @since 1.0
 */
@Component
public class SpringContext implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }
}
