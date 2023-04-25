package ca.jrvs.apps.grep;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public interface JavaGrep {
//    /**
//     *
//     * @throws IOException
//     */
//    void process() throws IOException;
//
//    /**
//     * Transverse the directory and return all files
//     * @param rootDir input directory
//     * @return files under root directory.
//     */
//    List<File> listFiles(String rootDir) throws IOException;
//
//    /**
//     * Read a file and return all the lines
//     *
//     * @param inputFile
//     * @return lines
//     * @throws IllegalArgumentException if a given inputfile is not a file
//     */
//    List<String> readLines(File inputFile) throws IOException;
//
//    /**
//     * Check if a line contains the regex pattern passed
//     * @param line input string
//     * @return true if there is a match
//     */
//    boolean containsPattern(String line);
//
//    /**
//     * Write lines to a file
//     *
//     * @param lines matched line
//     * @throws IOException if write failed
//     */
//    void writeToFile(List<String> lines) throws IOException;
//
//    String getRootPath();
//
//    void setRootPath(String rootPath);
//    String getRegex();
//    void setRegex(String regex);
//    String getOutFile();
//    void setOutFile(String outFile);
//}


import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * Transverse the directory and return all files
     *
     * @param rootDir input directory
     * @return files under root directory.
     * @throws IOException if listFiles failed
     */
    List<File> listFiles(String rootDir) throws IOException;

    /**
     * Read a file and return all the lines
     *
     * @param inputFile input file
     * @return lines
     * @throws IllegalArgumentException if a given inputfile is not a file
     * @throws IOException              if readLines failed
     */
    List<String> readLines(File inputFile) throws IOException;

    /**
     * Check if a line contains the regex pattern passed
     *
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     *
     * @param lines matched line
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    /**
     * Processes the files in root directory and write lines to outfile.
     *
     * @throws IOException if an error occurs while processing files or writing output
     */
    void process() throws IOException;

    /**
     * Returns the root directory of the JavaGrep app.
     *
     * @return root directory
     */
    String getRootPath();

    /**
     * Sets the root directory of the JavaGrep app.
     *
     * @param rootPath root directory
     */
    void setRootPath(String rootPath);

    /**
     * Returns the regex pattern used by the JavaGrep app.
     *
     * @return regex pattern
     */
    String getRegex();

    /**
     * Sets the regex pattern used by the JavaGrep app.
     *
     * @param regex regex pattern
     */
    void setRegex(String regex);

    /**
     * Returns the output file name used by the JavaGrep app.
     *
     * @return output file name
     */
    String getOutFile();

    /**
     * Sets the output file name used by the JavaGrep app.
     *
     * @param outFile output file name
     */
    void setOutFile(String outFile);

}
