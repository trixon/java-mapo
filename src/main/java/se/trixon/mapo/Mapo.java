/*
 * Copyright 2018 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.mapo;

import java.awt.GraphicsEnvironment;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.PomInfo;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.Xlog;

/**
 *
 * @author Patrik Karlström
 */
public class Mapo {

    private static String[] sArgs;
    private static final ResourceBundle sBundle = SystemHelper.getBundle(Mapo.class, "Bundle");
    private static Options sOptions;
    private CommandLine mCommandLine;

    public static String getHelp() {
        PrintStream defaultStdOut = System.out;
        StringBuilder sb = new StringBuilder()
                .append(sBundle.getString("usage")).append("\n\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp("xxx", sOptions, false);
        System.out.flush();
        System.setOut(defaultStdOut);
        sb.append(baos.toString().replace("usage: xxx" + System.lineSeparator(), "")).append("\n")
                .append(sBundle.getString("help_footer"));

        return sb.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sArgs = args;
        new Mapo();
    }

    public Mapo() {
        initOptions();

        if (sArgs.length == 0) {
            System.out.println(sBundle.getString("hint_tui"));
            displayGui();
        } else {
            if (mCommandLine.hasOption("help")) {
                displayHelp();
                System.exit(0);
            } else if (mCommandLine.hasOption("version")) {
                displayVersion();
                System.exit(0);
            } else {
                displayGui();
            }
        }

    }

    private void displayGui() {
        if (GraphicsEnvironment.isHeadless()) {
            Xlog.timedErr(Dict.Dialog.ERROR_NO_GUI_IN_HEADLESS.toString());
            System.exit(1);

            return;
        }

        new Thread(() -> {
            //MainApp.main(sArgs);
        }).start();
    }

    private void displayHelp() {
        System.out.println(getHelp());
    }

    private void displayVersion() {
        PomInfo pomInfo = new PomInfo(Mapo.class, "se.trixon", "mapo");
        System.out.println(String.format(sBundle.getString("version_info"), pomInfo.getVersion()));
    }

    private void initOptions() {
        Option help = Option.builder("h")
                .longOpt("help")
                .desc(sBundle.getString("opt_help_desc"))
                .build();

        Option version = Option.builder("v")
                .longOpt("version")
                .desc(sBundle.getString("opt_version_desc"))
                .build();

        sOptions = new Options();

        sOptions.addOption(help);
        sOptions.addOption(version);

        try {
            CommandLineParser commandLineParser = new DefaultParser();
            mCommandLine = commandLineParser.parse(sOptions, sArgs);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            System.out.println(sBundle.getString("parse_help"));
            System.exit(0);
        }
    }
}
