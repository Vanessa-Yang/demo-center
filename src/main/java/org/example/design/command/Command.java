package org.example.design.command;

/**
 * @Description Command Pattern Interface and Implementations
 * We can use interface or abstract class to create our base Command, It's a design decision
 * and depends on your requirement. We are going with interface because we don't have any
 * default implementations.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:43
 **/
public interface Command {
    void execute();
}
