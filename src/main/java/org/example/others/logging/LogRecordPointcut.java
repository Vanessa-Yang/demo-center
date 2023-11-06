//package org.example.others.logging;
//
//import org.springframework.aop.support.StaticMethodMatcherPointcut;
//import org.springframework.util.CollectionUtils;
//
//import java.io.Serializable;
//import java.lang.reflect.Method;
//
///**
// * @Description 切面
// * 基于注解的 AOP 在 Spring boot 1.5 中兼容性是有问题的，组件为了兼容 Spring boot 1.5 的版本
// * 我们手工实现 Spring 的 AOP 逻辑
// * @Author VanessaYang
// * @Date: 2023/5/8 0008 16:55
// **/
//public class LogRecordPointcut extends StaticMethodMatcherPointcut implements Serializable {
//
//    // logRecord 的解析类
//    private LogRecordOperationSource logRecordOperationSource;
//
//    @Override
//    public boolean matches(Method method, Class<?> targetClass) {
//        // 解析 这个 method 上有没有 @LogRecordAnnotation 注解，有的话会解析出来注解上的各个参数
//        return !CollectionUtils.isEmpty(logRecordOperationSource.computeLogRecordOperators(method, targetClass));
//        return false;
//    }
//
//    void setLogRecordOperationSource(LogRecordOperationSource logRecordOperationSource) {
//        this.logRecordOperationSource = logRecordOperationSource;
//    }
//
//
//}
