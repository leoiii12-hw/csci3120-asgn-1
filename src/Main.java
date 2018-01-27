import miniJava.MiniJavaParser;
import miniJava.MiniJavaParserConstants;
import miniJava.ParseException;
import miniJava.Token;

import java.util.*;


public class Main {

    public static void main(String args[]) throws ParseException {
        MiniJavaParser parser = new MiniJavaParser(System.in);
        Token token;

        Map<String, Integer> identifierFrequencyMap = new HashMap<>();
        Map<String, Integer> tokenFrequencyMap = new HashMap<>();

        while (true) {
            token = parser.getNextToken();

            switch (token.kind) {
                case MiniJavaParserConstants.INTEGER_LITERAL:
                    System.out.println("Line " + token.beginLine + ": " + token.image);
                    break;
                case MiniJavaParserConstants.IDENTIFIER:
                    identifierFrequencyMap.putIfAbsent(token.image, 1);
                    identifierFrequencyMap.computeIfPresent(token.image, (t, tfm) -> tfm + 1);
                    break;
                default:
                    tokenFrequencyMap.putIfAbsent(token.image, 1);
                    tokenFrequencyMap.computeIfPresent(token.image, (t, tfm) -> tfm + 1);
                    break;
            }

            if (token.kind == MiniJavaParserConstants.EOF) {
                break;
            }
        }

        System.out.println();
        System.out.println("Identifiers");
        for (Map.Entry<String, Integer> entry : MapUtils.sortMap(identifierFrequencyMap, false).entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());

        System.out.println();
        System.out.println("Other Tokens, Non integer + Non identifiers");
        for (Map.Entry<String, Integer> entry : MapUtils.sortMap(tokenFrequencyMap, false).entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }


}
