package core.singleton;

// 싱글톤의 핵심은 생성자를 private 로 막아두고, 유일한 인스턴스에 접근할 수 있는 public 메서드를 만드는 것이다.
public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private 로 막아서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다. (중요)
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
