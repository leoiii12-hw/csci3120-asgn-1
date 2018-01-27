import miniJava.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        if (args.length >= 1)
            System.setIn(new FileInputStream(args[0]));

        Scanner sc = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();
        while (sc.hasNextLine()) inputBuilder.append(sc.nextLine()).append("\n");
        String input = inputBuilder.toString();

        StringBuilder outputBuilder = new StringBuilder();

        try {
            processTokens(input, outputBuilder);
            System.out.print(outputBuilder.toString());
        } catch (TokenMgrError e) {
            handleCommentUnexpectedEOF(input, e);
        }
    }

    private static void handleCommentUnexpectedEOF(String input, TokenMgrError e) {
        String message = e.getMessage();

        // TODO: Don't rely on message
        if (message.contains("<EOF>") && message.contains("/*")) {
            String normalInput = input.substring(0, input.indexOf(e.getAfter()));
            int lineNumber = (normalInput.length() - normalInput.replace("\n", "").length());

            System.out.println("EOF in comment error. The comment begins at line " + lineNumber);
        }
    }

    private static void processTokens(String input, StringBuilder outputBuilder) {
        InputStream stream = new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8")));

        MiniJavaParser parser = new MiniJavaParser(stream);
        Token token;

        Map<String, Integer> identifierFrequencyMap = new HashMap<>();
        Map<String, Integer> tokenFrequencyMap = new HashMap<>();

        outputBuilder.append("Integer literals\n");
        while (true) {
            token = parser.getNextToken();

            switch (token.kind) {
                case MiniJavaParserConstants.EOF:
                    break;
                case MiniJavaParserConstants.INTEGER_LITERAL:
                    outputBuilder.append("Line ").append(token.beginLine).append(": ").append(token.image).append("\n");
                    break;
                case MiniJavaParserConstants.IDENTIFIER:
                    identifierFrequencyMap.computeIfPresent(token.image, (t, tfm) -> tfm + 1);
                    identifierFrequencyMap.putIfAbsent(token.image, 1);
                    break;
                case MiniJavaParserConstants.UNMATCHED_END_OF_COMMENT:
                    System.out.println("Unmatched */.  The comment begins at line " + token.beginLine);
                    break;
                case MiniJavaParserConstants.INVALID:
                    System.out.println("Line " + token.beginLine + ": Invalid character \'" + token.image + "\'");
                    break;
                default:
                    tokenFrequencyMap.computeIfPresent(token.image, (t, tfm) -> tfm + 1);
                    tokenFrequencyMap.putIfAbsent(token.image, 1);
                    break;
            }

            if (token.kind == MiniJavaParserConstants.EOF) {
                break;
            }
        }

        outputBuilder.append("\n");
        outputBuilder.append("Identifiers\n");
        for (Map.Entry<String, Integer> entry : MapUtils.sortMap(identifierFrequencyMap, false).entrySet())
            outputBuilder
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");

        outputBuilder.append("\n");
        outputBuilder.append("Other tokens, No integer literals + No identifiers\n");
        for (Map.Entry<String, Integer> entry : MapUtils.sortMap(tokenFrequencyMap, false).entrySet())
            outputBuilder
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
    }


}
