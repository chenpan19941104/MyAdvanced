package java.lang;

/**
 * @author cp
 * @create 2019-12-18 19:39
 */
public class String {
    public static void main(String[] args) {
        //双亲委派模型和沙箱安全机制  在父类的父类中找到的String类里并没有main方法，所以报错找不到
        System.out.println("*********");
    }
}
