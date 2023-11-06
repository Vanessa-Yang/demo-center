package org.example.annotations.test;

import org.example.annotations.MethodInfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Java Annotations Example
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 15:42
 **/
public class AnnotationExample {

    public static void main(String[] args) {
    }

    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2022", revision = 1)
    public String toString() {
        return "Overridden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 18 2022")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2012", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

}
