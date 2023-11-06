package org.example.design.command;

/**
 * @Description Command Pattern Receiver Classses
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:38
 **/
public interface FileSystemReceiver {

    void openFile();

    void writeFile();

    void closeFile();

}
