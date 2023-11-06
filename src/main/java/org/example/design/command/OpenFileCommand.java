package org.example.design.command;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:46
 **/
public class OpenFileCommand implements Command {
    private FileSystemReceiver fileSystem;

    public OpenFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        // open command is forwarding request to openFile method
        this.fileSystem.openFile();
    }
}
