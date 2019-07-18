package ru.academits.fedorov.csv;

import java.io.*;

public class Csv {
    public static void main(String[] args) throws IOException {
        System.out.println(1);
        CsvToHtml("CsvReader\\test.csv", "CsvReader\\out.html");
    }

    public static void CsvToHtml(String fileIn, String fileOut) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn));

        StringBuilder htmlStringBuilder = new StringBuilder();
        htmlStringBuilder.append("<html>");
        htmlStringBuilder.append("<table>");

        boolean isInsideString = false;
        boolean isLineBegin = true;
        int symbolInt = bufferedReader.read();
        while (symbolInt != -1) {
            if (isLineBegin){
                htmlStringBuilder.append("<tr><td>");
                isLineBegin = false;
            }
            char symbol = (char) symbolInt;

            if (isInsideString) {
                if (symbol == '\"') {
                    symbolInt = bufferedReader.read();
                    if (symbolInt == -1) {
                        htmlStringBuilder.append("</td><td>");
                        break;
                    }

                    symbol = (char) symbolInt;
                    if (symbol == '\"') {
                        htmlStringBuilder.append('\"');
                    } else if (symbol == ',') {
                        isInsideString = false;
                        htmlStringBuilder.append("</td><td>");
                    } else if (symbol == '\n') {
                        isInsideString = false;
                        htmlStringBuilder.append("</td></tr>\n");
                        isLineBegin = true;
                    }
                } else {
                    htmlStringBuilder.append(convertSymbol(symbol));
                }

            } else {
                if (symbol == '\"') {
                    isInsideString = true;
                } else {
                    if (symbol == ',') {
                        htmlStringBuilder.append("</td><td>");
                    } else if (symbol == '\n') {
                        htmlStringBuilder.append("</td></tr>\n");
                        isLineBegin = true;
                    } else {
                        htmlStringBuilder.append(convertSymbol(symbol));
                    }

                }
            }
            symbolInt = bufferedReader.read();
        }
        htmlStringBuilder.append("</table>");
        htmlStringBuilder.append("</html>");

        writeToHtml(fileOut, htmlStringBuilder);
    }

    private static String convertSymbol(char symbol){
        if (symbol == '<') {
            return "&lt";
        }
        if (symbol == '>') {
            return "&gt";
        }

        if (symbol == '&') {
            return "&amp";
        }
        return Character.toString(symbol);
    }

    private static void writeToHtml(String fileName, StringBuilder htmlStringBuilder) throws IOException {
        File fileOut = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut))) {
            writer.write(htmlStringBuilder.toString());
        }
    }
}
