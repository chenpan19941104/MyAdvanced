package com.study.lambda;
//如果接口内只有一个抽象方法，那么java底层认为他是个函数式接口
@FunctionalInterface
interface Foo{
//    public void sayHello();
    //public void say();
    public int add(int x,int y);

    default  int div(int x,int y){
        return x/y;
    }
    static int mul(int x,int y){
        return x*y;
    }
}

/**
 * 1.拷贝小括号，写死右箭头，落地大括号
 * 2.@FunctionalInterface
 * 3.default默认方法
 * 4.staic静态方法
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello");
            }
        };
        foo.sayHello();
        Foo foo1 = () -> System.out.println("hello");
        foo1.sayHello();*/

        Foo foo=(x,y)-> {
            System.out.println(x+","+y);
            return x+y;
        };
        System.out.println(foo.add(3, 5));
        System.out.println(foo.div(6, 3));
        System.out.println(Foo.mul(4, 5));
    }
}
