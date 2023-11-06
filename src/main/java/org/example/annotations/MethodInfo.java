package org.example.annotations;

import java.lang.annotation.*;

/**
 * @Description custom annotion created by us
 * @Author VanessaYang
 * @Date: 2023/4/10 0010 15:38
 **/
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "Pankaj";

    String date();

    int revision() default 1;

    String comments();
}
