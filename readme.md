## Word counter

This is an application, that takes path to file as an argument and prints out a binary tree with occurrences of words and sums of them.
Requires java 8 to compile and run.

## Usage

```bash
wordCounter $ mkdir target
wordCounter $ javac src/org/tirnak/* -d target
wordCounter $ java -Dfile.encoding=UTF8 -classpath target org.tirnak.Counter sample.txt
```

Instead of sample.txt you can substitute any other text file
