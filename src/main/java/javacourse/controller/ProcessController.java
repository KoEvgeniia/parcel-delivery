package javacourse.controller;

import javacourse.domain.InputParm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ProcessController {
    void process(InputParm inputParm);

    void consoleInfo();

    Pattern fileComandPattern();

    InputParm getInputParm(Matcher matcher);
}
