package org.example.others.logging;

import javax.swing.text.Element;
import java.lang.annotation.*;

/**
 * @Description 操作日志
 * @Author VanessaYang
 * @Date: 2023/5/8 0008 16:47
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {

    // 必填，操作日志的文本模板
    String success();

    // 选填，操作日志失败的文本版本
    String fail() default "";

    // 操作日志的执行人
    String operator() default "";

    // 必填，操作日志绑定的业务对象标识
    String bizNo();

    // 操作日志的种类
    String category() default "";

    // 扩展参数，记录操作日志的修改详情
    String detail() default "";

    // 记录日志的条件
    String condition() default "";

}
