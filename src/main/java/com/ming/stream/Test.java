package com.ming.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *  现在有五个用户需要筛选：
 *  1、ID 必须是偶数
 *  2、年龄必须大于23岁
 *  3、用户名转为大写字母
 *  4、用户名字母倒着排序
 *  5、只输出一个用户！
 *
 */

public class Test {
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",24);
        User u4 = new User(4,"d",26);
        User u5 = new User(6,"e",27);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream()
                .filter(u->{return u.getId()%2==0;})  //断定型接口
                .filter(user -> {return user.getAge()>23;})
                .map(user -> {
                    user.setName(user.getName().toUpperCase(Locale.ROOT)); return user;
                }) //函数式接口
                .sorted((uu1,uu2) -> {return uu2.getName().compareTo(uu1.getName());})
                .limit(1)
                .forEach(System.out::println);

    }


}
