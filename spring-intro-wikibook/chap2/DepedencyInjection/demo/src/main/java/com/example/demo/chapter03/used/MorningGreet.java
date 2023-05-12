package chapter03.used;

public class MorningGreet implements Greet{
    @Override
    public void greeting() {
        System.out.println("---------------");
        System.out.println("좋은 아침입니다.");
        System.out.println("---------------");
    }
}
