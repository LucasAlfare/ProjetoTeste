package gerador.core;

import org.jfugue.integration.LilyPondParserListener;
import org.jfugue.parser.Parser;
import org.staccato.StaccatoParser;
import org.staccato.StaccatoParserListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kk {

    public static void main(String[] args) {
        StaccatoParser parser = new StaccatoParser();
        LilyPondParserListener listener = new LilyPondParserListener();
        parser.addParserListener(listener);
        parser.parse("G#5i Ri G#5i Ri G#5i Ri G#5i Ri");
        System.out.println(listener.getLyString());
    }

    class MyParser extends Parser {

        public void parse(String staccato){

        }
    }
}
