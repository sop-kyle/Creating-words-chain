package com.javarush.task.task22.task2209;

import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder("");

        try (Scanner scan = new Scanner(System.in);
                BufferedReader reader = new BufferedReader(new FileReader(scan.nextLine()));) {
            while (reader.ready()) {
                str.append(reader.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder result = getLine(str.toString().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0)
            return new StringBuilder("");
        List<String> wordsList = new LinkedList(Arrays.asList(words));
        StringBuilder resultString = new StringBuilder("");
        int random = (int)(wordsList.size() * Math.random());
        resultString.append(wordsList.remove(random));
        int countBadWords = 0;
        while (!wordsList.isEmpty() && countBadWords <= wordsList.size()) {
            StringBuilder temp = resultString;
            String firstWord = wordsList.get(0);
            if (firstWord.substring(0, 1).equalsIgnoreCase(temp.substring(temp.length() - 1)))
                resultString.append(" ").append(firstWord);
            else if (firstWord.substring(firstWord.length() - 1).equalsIgnoreCase(temp.substring(0, 1)))
                resultString.insert(0, firstWord + " ");
            else {
                wordsList.add(firstWord);
                countBadWords ++;
            }
            wordsList.remove(0);
        }
        for (String word: wordsList) {
            resultString.append(" ").append(word);
        }
        return resultString;
    }
}
