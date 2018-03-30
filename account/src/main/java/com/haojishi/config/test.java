package com.haojishi.config;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List list =new ArrayList();
        list.add("A1");
        list.add("A2");
        list.add("A3");
        list.add("A4");
        list.add("A5");
        list.add("A6");
        list.add("A7");
        list.add("A8");
        list.add("A9");
        List list1 =new ArrayList();
        list1.add("B1");
        list1.add("B2");
        list1.add("B3");
        list1.add("B4");
        list1.add("B5");
        list1.add("B6");
        list1.add("B7");
        list1.add("B8");
        list1.add("B9");
        List list2 =new ArrayList();
        list2.add("C1");
        list2.add("C2");
        list2.add("C3");
        list2.add("C4");
        list2.add("C5");
        list2.add("C6");
        list2.add("C7");
        list2.add("C8");
        list2.add("C9");
        List list3 =new ArrayList();
        list3.add("D1");
        list3.add("D2");
        list3.add("D3");
        list3.add("D4");
        list3.add("D5");
        list3.add("D6");
        list3.add("D7");
        list3.add("D8");
        list3.add("D9");

        List listes =new ArrayList();
        for(int i = 0;i < list.size();i++){

        }
        listes.add(0,list.get(0));

//        for(int i = 0;i < list1.size();i++){
//            for(int j = 1;j < list1.size();j++){
//                listes.add(list1.get(i));
//                listes.add(list1.get(j));
//
//                for(int a = 0;a < list.size();a++){
//                    listes.add(list.get(a));
//
//                    for (int b = 0;b < list2.size();b++){
//                        listes.add(list2.get(b));
//
//                        for (int c = 0;c < list3.size();c++){
//                            listes.add(list3.get(c));
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        System.out.println(listes);

    }


    }
