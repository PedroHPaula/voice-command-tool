
import singleton.JuliusProcess;
import singleton.MyRobot;
import strategy.ControlCommand;
import strategy.PressCommand;
import strategy.TerminalCommand;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class VoiceCommandToolApp {
    private static final Double passScore = 0.700;

    public static void main(String[] args) throws IOException {

        // Creates a buffer to read Julius output/error as a string
        InputStream inputStream = JuliusProcess
                                    .getInstance()
                                    .getProcess()
                                    .getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String juliusOutputLine;
        String keyword = null;
        String command;
        List<String> scores;
        List<String> sentenceWords = new ArrayList<>();
        double minScore;

        // Instantiate the types of recognizable commands
        TerminalCommand terminalCommand = new TerminalCommand();
        ControlCommand controlCommand = new ControlCommand();
        PressCommand pressCommand = new PressCommand();

        // Flag to determine if sentence validation is necessary
        boolean checkScore = false;

        System.out.println("Script initialized");
        System.out.println("Please speak");

        while ((juliusOutputLine = bufferedReader.readLine()) != null) {
            // If no validation was made, store the current
            // recognized sentence and set the flag to validate it
            if (!checkScore) {
                if (juliusOutputLine.startsWith("sentence1: <s>")) {
                    checkScore = true;
                    sentenceWords = Arrays.asList(juliusOutputLine
                            .replace("sentence1: <s> ", "")
                            .replace(" </s>", "")
                            .split(" "));
                    keyword = sentenceWords.get(0);
                    
                }
                // Unset the flag and validate the sentence
            } else {
                if (juliusOutputLine.startsWith("cmscore1: ")) {
                    checkScore = false;
                    scores = Arrays.asList(juliusOutputLine
                            .replace("cmscore1: ", "")
                            .replace("\n", "")
                            .split(" "));

                    Collections.sort(scores);
                    minScore = Double.parseDouble(scores.get(0));

                    // Pass score that determines if the spoken sentence is valid
                    if (minScore >= passScore) {
                        switch (keyword) {

                            case "COMMAND":
                            case "TERMINAL":
                                command = terminalCommand.getCommand(sentenceWords.get(1));
                                if (command != null) {
                                    if (command.equals("silence")) {
                                        System.out.println(sentenceWords + " - " + minScore);
                                        System.out.println("Exiting the script");
                                        // Attempts to kill Julius normally and, in case it doesn't work,
                                        // attempts to kill it forcibly
                                        if (!JuliusProcess.getInstance().killProcess()) {
                                            JuliusProcess.getInstance().killProcessForcibly();
                                        }
                                        System.exit(0);
                                    } else {
                                        System.out.println(sentenceWords + " - " + minScore);
                                        MyRobot.getInstance().typeString(command);
                                    }
                                }
                                break;

                            case "CONTROL":
                                command = controlCommand.getCommand(sentenceWords.get(1));
                                if (command != null) {
                                    System.out.println(sentenceWords + " - " + minScore);
                                    MyRobot.getInstance().keyPress(KeyEvent.VK_CONTROL);
                                    MyRobot.getInstance().typeString(command);
                                    MyRobot.getInstance().keyRelease(KeyEvent.VK_CONTROL);
                                }
                                break;

                            case "PRESS":
                                command = pressCommand.getCommand(sentenceWords
                                        .stream()
                                        .filter(s -> !s.equals("PRESS"))
                                        .collect(Collectors.joining(" "))
                                );
                                if (command != null) {
                                    System.out.println(sentenceWords + " - " + minScore);
                                    MyRobot.getInstance().tapKey(command);
                                }
                                break;

                            default:
                                System.out.println("The keyword " + keyword + " is not supported");
                                break;
                        }

                    } else {
                        System.out.println("The sentence couldn't be validated");
                        System.out.println("Please try speaking again");
                    }
                }
            }
        }
    }
}
