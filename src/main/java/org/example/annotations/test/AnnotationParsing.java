package org.example.annotations.test;

import com.sun.source.tree.YieldTree;
import org.example.annotations.MethodInfo;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description Java Annotations Parsing
 * Use Reflection to parse java annotations from a class.
 * Note that Annotation Retention Policy should be RUNTIME otherwise its information will not be
 * available at runtime and we won't be able to fetch any data from it.
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 15:48
 **/
public class AnnotationParsing {
    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsing.class.getClassLoader()
                    .loadClass("org.example.annotations.test.AnnotationExample").getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(org.example.annotations.MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' ： " + anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = " + method);
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
