package org.example.design.command;

/**
 * @Description Command Pattern Invoker Class
 * Invoker is a simple class that encapsulates the Command and passes the request to the command
 * object to process it.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:50
 **/
public class FileInvoker {

    public Command command;

    public FileInvoker(Command command) {
        this.command = command;
    }

    public void execute(){
        this.command.execute();
    }
}
