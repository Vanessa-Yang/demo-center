package org.example.algorithms.learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description CAS锁
 * @Author VanessaYang
 * @Date: 2023/8/6 0006 13:55
 **/
public class CasTest {

    public static void main(String[] args) {
        Entity entity = new Entity(0);
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        boolean successful;

        // 4个参数分别是：对象实例、字段的内存偏移量、字段期望值、字段新值
        successful = unsafe.compareAndSwapInt(entity, offset, 0, 3); // 将entity.x从0改为3
        System.out.println(successful + "\t" + entity.getX());

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 5); // 将entity.x从3改为5
        System.out.println(successful + "\t" + entity.getX());

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 8); // 将entity.x从3改为8
        System.out.println(successful + "\t" + entity.getX());
    }
}

class UnsafeFactory {

    /**
     * 获取 Unsafe 对象
     *
     * @return
     */
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段的内存偏移量
     *
     * @param unsafe
     * @param clazz
     * @param fieldName
     * @return
     */
    public static long getFieldOffset(Unsafe unsafe, Class clazz, String fieldName) {
        try {
            return unsafe.objectFieldOffset(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Entity {
    private int x;
}
