package org.example.design.command;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 14:48
 **/
public class CloseFileCommand implements Command{
    private FileSystemReceiver fileSystem;

    public CloseFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.closeFile();
    }
}
