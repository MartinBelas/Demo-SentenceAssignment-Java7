# Java Coding Assignment

The following is a small coding exercise.

## Assignment

The application must be able to read from standard input and write onto standard output. 
The solution must be written in Java version 1.7.

### Functionality

The basic requirement is to provide a program that can convert text into either XML formatted data or comma separated (CSV) data. 
The text is to be parsed, broken into sentences and words and the words have to be sorted.

Consider huge input when dealing IO operations (entire dataset cannot fit in heap).

The program must be able to read input text like:
```
“Mary had a little lamb. Peter called for the wolf, and Aesop came.
Cinderella likes shoes.”
```

and parse this text into relevant model classes, and be able to convert the structure to both XML and CSV format.

The parsing must break the text into sentences and words. 
The parser should allow some whitespace around words and delimiters.

The XML result should be like:
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<text>
    <sentence>
        <word>a</word>
        <word>had</word>
        <word>lamb</word>
        <word>little</word>
        <word>Mary</word>
    </sentence>
    <sentence>
        <word>Aesop</word>
        <word>and</word>
        <word>called</word>
        <word>came</word>
        <word>for</word>
        <word>Peter</word>
        <word>the</word>
        <word>wolf</word>
    </sentence>
    <sentence>
        <word>Cinderella</word>
        <word>likes</word>
        <word>shoes</word>
    </sentence>
</text>
```

The CSV result should likewise be:
```
, Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8
Sentence 1, a, had, lamb, little, Mary
Sentence 2, Aesop, and, called, came, for, Peter, the, wolf
Sentence 3, Cinderella, likes, shoes
```

## Installation

Standard gradle way.

## Testing the conversions

It is possible to test the conversion process by running tests from the "src/integrationTest/java" folder.
The tests take input files from "src/integrationTest/resources" folder, procces them and put result files to the same folder.

## Algorithm of the application

When you run the application, it will process in the following steps:

1. Asks for the name (and location) of input file with the text.

2. Asks for output file type, for example XML or CSV

3. Then it selects select the conversion strategy, according to output file type. The input file type is "Sentence" by default.
        
4. Creates output file name accordin the input file name, but with the right extension (.csv or .xml).

5. Then start the conversion and export to output file. Tis task is processed by the selected strategy.

6. Finally it writes the output file location

