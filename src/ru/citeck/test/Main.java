package ru.citeck.test;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	    System.out.println(task2("([][[]()])"));
        System.out.println(task3(120307));
        int[] numbers = {1,2,2,3,4,9,4,9,6,8,7,-5,6,7,8,9};
        System.out.println(task1(numbers));
    }

    static LinkedList<LinkedList<Integer>> task1 (int[] numbers) {
        LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            int first = numbers[i];
            int last = 1;
            while (i+1 < numbers.length && numbers[i+1] == first) {
                ++last;
                ++i;
            }
            if (result.size() == 0) {
                result.add(new LinkedList<Integer>());
                result.get(0).addFirst(first);
                result.get(0).addLast(last);
            } else if (result.getFirst().getLast() > last ||
                    result.getFirst().getLast() == last && result.getFirst().getFirst() > first) {
                result.addFirst(new LinkedList<Integer>());
                result.getFirst().addFirst(first);
                result.getFirst().addLast(last);
            } else if (result.getLast().getLast() < last ||
                    result.getLast().getLast() == last && result.getLast().getFirst() < first) {
                result.addLast(new LinkedList<Integer>());
                result.getLast().addFirst(first);
                result.getLast().addLast(last);
            } else {
                for (int j = 1; j < result.size(); j++) {
                    if (result.get(j-1).getLast() <= last && result.get(j).getLast() > last) {
                        result.add(j, new LinkedList<Integer>());
                        result.get(j).addFirst(first);
                        result.get(j).addLast(last);
                        break;
                    }
                }
            }
        }
        return result;
    }

    static boolean task2 (String inText) {
        StringBuffer text = new StringBuffer(inText);
        String a = "()";
        String b = "[]";
        while (text.length() != 0) {
            int indexA = text.indexOf(a);
            int indexB = text.indexOf(b);
            if (indexA >= 0 || indexB >= 0) {
                if (indexA >= 0) {
                    text.delete(indexA, indexA+2);
                }
                if (indexB >= 0) {
                    text.delete(indexB, indexB+2);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    static int task3 (int num) {
        StringBuffer text = new StringBuffer(Integer.toString(num));
        if (text.lastIndexOf("0") >= 0) {
            text.replace(text.lastIndexOf("0"), text.lastIndexOf("0") + 1, "1");
        }
        return Integer.parseInt(String.valueOf(text));
    }

    /*
    * Задание 4.
    * А.
    * Я использовал MySQL5.6 на http://sqlfiddle.com
    * Самый поздний стандарт, который упомянается в документации - SQL:2011
    *
    * B.
    * CREATE TABLE testTable (num INT NOT NULL);
    * INSERT testTable VALUES ('-11'), ('3'), ('6'), ('11'), ('12');
    *
    * C.
    * SELECT num + 1 AS first, (SELECT num FROM testTable WHERE num > first LIMIT 1) - num - 1 AS skip
    * FROM testTable
    * WHERE (num+1) NOT IN (SELECT num FROM testTable) AND (num+1) < ANY (SELECT num FROM testTable);
    * */

}
