package org.example.design.proxy;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/7 0007 13:34
 **/
public class CommandExecutorImpl implements CommandExecutor {
    @Override
    public void runCommand(String cmd) throws Exception {
        // some heavy implementation
//        Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' command executed.");
    }
}
