package org.example.design.command;

/**
 * @Description Each Command implementation will forward the request to the appropriate method of receiver.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:48
 **/
public class WriteFileCommand implements Command {
    private FileSystemReceiver fileSystem;

    public WriteFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}
