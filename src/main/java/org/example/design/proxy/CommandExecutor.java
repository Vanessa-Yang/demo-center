package org.example.design.proxy;

/**
 * @Description Proxy design pattern: Provide a surrogate or placeholder for another
 * object to control access to it.
 * Proxy Design Pattern - Main Class
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 11:29
 **/
public interface CommandExecutor {

    void runCommand(String cmd) throws Exception;
}
