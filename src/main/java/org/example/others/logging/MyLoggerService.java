package org.example.others.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/6 0006 16:51
 **/
public class MyLoggerService {

    static final Logger LOGGER_1 = LoggerFactory.getLogger(MyLoggerService.class);

    public static void main(String[] args) {
        LOGGER_1.info("Entering application");

        Foo foo = new Foo();
        foo.doIt();
        foo.doBusiness();
        LOGGER_1.info("Exiting application.");
    }

}

class Foo {
    static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);
    static final Logger businessLog = LoggerFactory.getLogger("businessLog");

    public void doIt() {
        MDC.put("userId", "111");
        LOGGER.debug("Did it again!");
    }

    public void doBusiness() {
        businessLog.info("Did business!");
        businessLog.debug("Did business: on debugging!");
    }
}