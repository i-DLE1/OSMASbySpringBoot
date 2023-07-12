package com.idle.osmas;

import com.idle.osmas.configuration.MybatisConfiguration;
import com.idle.osmas.configuration.OsmasApplication;
import com.idle.osmas.configuration.SpringSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {MybatisConfiguration.class, SpringSecurityConfiguration.class, ContextConfiguration.class, OsmasApplication.class})
class OsmasApplicationTests {

    @Test
    void contextLoads() {
    }

}
