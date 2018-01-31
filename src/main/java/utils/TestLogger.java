package utils;

import org.apache.log4j.*;

public class TestLogger {

  private static TestLogger testLogger;
     private Logger logger;

    private TestLogger(String testName, String className,  String packageName) {

        logger = Logger.getLogger(testName);
        logger.setLevel(Level.toLevel("DEBUG"));
        BasicConfigurator.resetConfiguration();

        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(String.format("%s/test-output/logs/%s/%s/%s.log", System.getProperty("user.dir"), packageName, className, testName));
        fileAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        fileAppender.setAppend(false);
        fileAppender.setName("FileAppender");
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.activateOptions();
        logger.addAppender(fileAppender);

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        consoleAppender.setName("ConsoleAppender");
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.activateOptions();
        logger.addAppender(consoleAppender);
    }

    public static TestLogger getLogger(String testName, String className, String packageName) {
        if (testLogger == null) {
            testLogger = new TestLogger(testName, className, packageName);
        }
        return testLogger;
    }

    public static TestLogger getLogger() {
        if (testLogger == null) {
            System.out.println("getLogger is not executed, test name is not set up");
        }
        return testLogger;
    }


    public void info(Object message) {
        logger.info(message);
    }

    public void debug(Object message) {
        logger.debug(message);
    }

    public void error(String message) {
        logger.error(message);
    }


}
