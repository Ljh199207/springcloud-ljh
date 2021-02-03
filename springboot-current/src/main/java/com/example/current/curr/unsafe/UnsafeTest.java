package com.example.current.curr.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe.toString());

        User user = new User();
        //10
        System.out.println(user.age);

        //age将返回0，因为 Unsafe.allocateInstance()只会给对象分配内存，并不会调用构造方法，所以这里只会返回int类型的默认值0。
        User user1 = (User) unsafe.allocateInstance(User.class);
        System.out.println(user1.age);

        //使用Unsafe的putXXX()方法，我们可以修改任意私有字段的值。
        Field field = user.getClass().getDeclaredField("age");
        //反射直接改
        field.set(user,30);
        System.out.println(user.age);
        unsafe.putInt(user,unsafe.objectFieldOffset(field),20);
        System.out.println(user.age);
    }




    static class User {
        int age;
        public User() {
            this.age = 10;
        }
    }
}
