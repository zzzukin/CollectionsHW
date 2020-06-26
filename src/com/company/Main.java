package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class MyList <Type> implements Iterable<Type>{
    private Type [] arrayList;
    private Integer currentSize;

    public MyList (Type [] newArray){
        arrayList = newArray;
        currentSize = newArray.length;
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private Integer currIdx = currentSize - 1;

            @Override
            public boolean hasNext() {
                return currIdx >= 0 && currIdx < currentSize && arrayList[currIdx] != null;
            }

            @Override
            public Type next() {
                return arrayList[currIdx--];
            }
        };
        return it;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        String InStr = new String(Files.readAllBytes(Paths.get("input.txt")));

        String InStrCpy = InStr;

        InStr = InStr.replaceAll("\r\n", " ");

        while(InStr.contains("  ")){
            InStr = InStr.replace("  ", " ");
        }

        while(InStr.contains(",")){
            InStr = InStr.replace(",", "");
        }

        while(InStr.contains(".")){
            InStr = InStr.replace(".", "");
        }

        while(InStr.contains("-")){
            InStr = InStr.replace("-", "");
        }

        String[] WordsArr = InStr.split(" ");


/*        for (String word : WordsArr){
            System.out.println(word);
        }*/
        //System.out.print(InStr);

        //считаем кол-во различных слов в строке
        HashSet<String> WordsSet = new HashSet<>(Arrays.asList(WordsArr));
        System.out.format("Input text has %d words\n", WordsSet.size());

        //сортируем по длинне слов
        Arrays.sort(WordsArr, Comparator.comparingInt(String::length));

        System.out.println("Sorted by string length:");
        for(String Word : WordsArr) {
            System.out.println(Word);
        }

        System.out.println("");
        System.out.println("Sorted by lexicographically:");
        Arrays.sort(WordsArr);

        for(String Word : WordsArr) {
            System.out.println(Word);
        }

        TreeMap<String, Integer> WordsNum = new TreeMap<>();

        for(String Word : WordsArr) {
            if (WordsNum.containsKey(Word)) {
                Integer Val = WordsNum.get(Word);
                WordsNum.put(Word, Val + 1);
            } else {
                WordsNum.put(Word, 1);
            }
        }

        System.out.println("");
        System.out.println("Words numbers:");
        for(String Word : WordsNum.keySet()) {
            System.out.println(Word + "-" + WordsNum.get(Word).toString());
        }

        String []StringsArr = InStrCpy.split("\n");

        Collections.reverse(Arrays.asList(StringsArr));

        System.out.println("");
        System.out.println("Strings in revers order:");
        for(String Word : StringsArr) {
            System.out.println(Word);
        }

        String []NextStringsArr = InStrCpy.split("\n");
        MyList <String> RevStringList = new MyList <String> (NextStringsArr);

        System.out.println("");
        System.out.println("Strings in revers order by revers iterator: ");
        for(String Word : RevStringList) {
            System.out.println(Word);
        }

        int StrNum = 0;
        System.out.println("");
        for(String Word : NextStringsArr) {
            System.out.format("%d. %s\n", StrNum, Word);
            StrNum++;
        }

        Scanner Scan = new Scanner(System.in);
        Integer StringsNum = NextStringsArr.length;

        System.out.println("");
        System.out.format("Input string number in range %d to %d: ", 0, StringsNum - 1);
        if (Scan.hasNextInt()) {
            int i = Scan.nextInt();
            if(i >= 0 && i < StringsNum){
                System.out.println(NextStringsArr[i]);
            }
            else {
                System.out.println("Input string number is incorrect!");
            }
        }
    }
}
