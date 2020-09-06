package demo;

import demo.converters.strategy.ConverterStrategy;
import demo.converters.strategy.ConverterStrategyFactory;
import demo.domain.FileType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class App {

    final static String DEFAULT_FILE_PATH = "data.in";

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // 1. ask for input file name (default "data.in" file in the App folder)
        File inputFile = askForInputFile();
        if (!inputFile.exists()) {
            String errMsg = String.format("File %s does not exist.", inputFile);
            logger.error(errMsg);
            System.out.println(errMsg);
            System.exit(-1);
        }

        // 2. ask for output file type, for example xml or csv (from enum)
        FileType outputFileType = null;
        while (outputFileType == null) {
            outputFileType = askForOutputFileType();
        }
        logger.info("Output File Type: {}", outputFileType.name());

        // 3. select the conversion strategy
        ConverterStrategy strategy = new ConverterStrategyFactory().getStrategy(inputFile, outputFileType);

        // 4. start the conversion and export to output file
        File outputFile = strategy.convert(inputFile);

        // 5. write the output file location
        System.out.println("Finished.");
        if (outputFile != null) {
            System.out.println("Output file: " + outputFile.getAbsolutePath());
        } else {
            System.out.println("Nothing converted.");
        }
    }

    /*
     * Asks for input file. If nothing entered then returns default file data.in
     */
    private static File askForInputFile() {

        String inputFileName;

        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        System.out.println(
                String.format("Please enter input file name (or press ENTER to use default %s) : ", DEFAULT_FILE_PATH));
        inputFileName = in.nextLine();

        if (!inputFileName.isEmpty()) {
            inputFileName = inputFileName.trim();

        } else {
            System.out.println(String.format("Input file not provided, we will use %s", DEFAULT_FILE_PATH));
            inputFileName = DEFAULT_FILE_PATH;
        }

        File f = new File(inputFileName);

        logger.info("Input file name: {}", inputFileName);

        return f;
    }

    private static FileType askForOutputFileType() {

        FileType result = null;

        while (result == null) {

            System.out.println("Please select output file type: ");

            for (FileType t : FileType.values()) {
                System.out.println(String.format("%d - %s", t.getCode(), t.name()));
            }

            System.out.println("Please enter the number: ");

            @SuppressWarnings("resource")
            Scanner s = new Scanner(System.in);

            FileType found = null;
            if (s.hasNextInt()) {
                int input = s.nextInt();
                for (FileType t : FileType.values()) {
                    if (t.getCode() == input) {
                        found = t;
                        break;
                    }
                }
            }
            result = found;
        }

        return result;
    }
}
