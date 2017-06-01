package org.tirnak;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter {

    private static Pattern wordsRegexp = Pattern.compile("\\w+");

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 1) {
            throw new RuntimeException("Path to file is not provided." + getUsageDescription());
        }
        Path pathToWordsFile = Paths.get(args[0]);
        if (!Files.exists(pathToWordsFile)) {
            throw new RuntimeException("Path to file is incorrect." + getUsageDescription());
        }
        List<String> fileContent = Files.readAllLines(pathToWordsFile);
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String line : fileContent) {
            Matcher matcher = wordsRegexp.matcher(line);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                if (!wordsMap.containsKey(word)) {
                    wordsMap.put(word,1);
                } else {
                    wordsMap.put(word, wordsMap.get(word) + 1);
                }
            }
        }
        LinkedList<Node<Integer>> nodes = new LinkedList<>();
        for (Map.Entry<String, Integer> wordOccurrence : wordsMap.entrySet()) {
            nodes.add(new NamedLeaf<>(wordOccurrence.getKey(), wordOccurrence.getValue()));
        }

        Collections.sort(nodes, (node1, node2) -> node1.getValue().compareTo(node2.getValue()));

        while (nodes.size() > 1) {
            Node<Integer> firstChild = nodes.removeFirst();
            Node<Integer> secondChild = nodes.removeFirst();
            int sum = firstChild.getValue() + secondChild.getValue();
            Node<Integer> newParentNode = new BinaryNode<>(sum);
            newParentNode.addChild(firstChild);
            newParentNode.addChild(secondChild);
            int newIndex = 0;
            while (!nodes.isEmpty() && nodes.get(newIndex).getValue() < newParentNode.getValue() && newIndex < nodes.size() - 1) { newIndex++; }
            nodes.add(newIndex, newParentNode);
        }

        Node<Integer> root = nodes.get(0);

        PrintStream stdout = new PrintStream(System.out, true, "UTF-8");

        root.print(stdout);


        Thread.currentThread().sleep(1000);
    }

    private static String getUsageDescription() {
        return "\nUsage: java Counter [path to file]";
    }
}
