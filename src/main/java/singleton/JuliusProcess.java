package singleton;

import java.io.*;
/*
* This class returns an object for the process started by Julius.
* It is implemented using a Singleton i.e. it is only instantiated
* a single time during the whole execution of the script. This is
* advantageous because once Julius is running we don't need to make
* any changes to it, only finish it when we want to exit the script.
* */
public class JuliusProcess {
    private final String juliusPath = "./src/main/resources/julius";
    private final String configPath = "./src/main/resources/conf.jconf";
    private static final JuliusProcess instance;
    private Process process;

    static {
        try {
            instance = new JuliusProcess();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JuliusProcess() throws IOException {
        super();
        String[] command = {juliusPath,"-C",configPath};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        this.setProcess(processBuilder.start());
    }
    public static JuliusProcess getInstance() { return instance; }

    public Process getProcess() { return process; }

    private void setProcess(Process process) { this.process = process; }

    public boolean killProcess() { return process.toHandle().destroy(); }

    public boolean killProcessForcibly() { return process.toHandle().destroyForcibly(); }

}
