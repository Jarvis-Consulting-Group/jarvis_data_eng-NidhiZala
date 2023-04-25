package ca.jrvs.apps.grep.practice;
import javax.sound.sampled.Line;
import java.util.regex.*;
public class RegexExcImp implements RegexExc {
    @Override
    public boolean matchJpeg(String filename) {
        Pattern p = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g))$)");
        Matcher m = p.matcher(filename);
        return m.matches();
    }

    @Override
    public boolean matchIP(String ip) {
        Pattern p = Pattern.compile("((?:[0-9]{1,3}\\.){3}[0-9]{1,3})");
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    @Override
    public boolean isLineEmpty(String line) {
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(line);
        return m.matches();
    }

    public static void main(String[] args) {

        System.out.println(new RegexExcImp().matchJpeg("abc.jpg"));
        System.out.println(new RegexExcImp().matchIP("255.999.255.999"));
        System.out.println(new RegexExcImp().isLineEmpty(" "));
    }

}
