# TIL3

오늘은 자바의 인터페이스와 관련된 내용을 공부했다.

예전에 자바 스프링부트와 스프링 데이터 JPA를 사용하여 웹프로젝트를 했었는데 인터페이스를 필수로 사용해야 했다. 오늘 따로 공부를 하며 인터페이스의 기능을 확실하게 이해할 수 있었다.

또 디폴트 메소드를 공부햇는데, 자바 8 이상부터는 불편한 점을 많이 해소할 수 있는 여러가지 기능을 제공하는 구나 깨달으면서 디폴트 메소드 또한 인터페이스를 사용하면서 생겨난 불편한 점들을 해결할 수 있다는 사실을 알게 되었다.

추가적으로 함수형 인터페이스, 익명 클래스, 람다 표현식을 공부했는데, 이 부분에 대해서는 어렴풋이 알고 있었지만 막상 공부를 해보니 정말 어려웠다. 함수형 인터페이스와 익명클래스의 특성을 살려야 람다표현식으로 바꿀 수 있다는 사실을 이번에 알게 되었다. 여기서도 계속적으로 DI개념이 나오게 되는데, 사실 메소드의 인자로 함수를 넣는다는게 정말 와닿지 않았다(consumer, predicate 등등..). 하지만 이부분 또한 꼭 익숙해져서 내것으로 만들어야 겠다고 생각했다.

특히 람다 표현식에서 메소드 레퍼런스를 사용하는데, 아직까지는 익숙하지 않아서 계속해서 람다표현식을 만들어보고 메소드 레퍼런스로 바꿀 수 있다면 매번 바꿔보면서 체화해야 겠다.

---

### Article

[원본 보기 : https://engineer-diary.tistory.com/62?category=999296, https://engineer-diary.tistory.com/63?category=999296]

### 디폴트 메서드 란?

현재 인터페이스는 굉장히 많은 형태로 사용되고 있다.

그러다보니 인터페이스의 단점들이 드러나기 시작하였고 그것을 해결하기위해 나온것이 디폴트 메서드(default method) 이다.



우선 default method의 역할을 살펴보자.

디폴트 메소드를 사용하면 인터페이스가 구현체(메소드의 구현부)를 가질 수 있게 된다.

```java
interface MyInterface { // 추상 메소드로만 이뤄진 클래스 == 인터페이스
    void method1(); // 구현이 없다 : 추상 메소드

    default void sayHello() { // 구현이 있다 (자바 8 부터 이런 구현 메소드를 인터페이스가 가질 수 있다) 대신 default 키워드를 써줘야 함
        System.out.println("Hello World");
    }
}

public class Main implements MyInterface{
    public static void main(String[] args) {
        new Main().sayHello();
    }

    @Override
    public void sayHello() {
        System.out.println("Bye World");
    }

    @Override
    public void method1() {
        throw new RuntimeException();
    }
}
```

MyInterface라는 인터페이스에 sayHello 디폴트 메소드로 선언하여 인터페이스 안에서 sayHello 메소드를 구현하고 있다. 이게 바로 디폴트 메소드의 역할이다.



그럼 이 디폴트 메소드는 도대체 왜 등장하게 된 것일까?

아래 예시를 살펴보자.

`MyInterface.java`

```java
interface MyInterface {
    void method1();
    void method2();
    void method3();
    void method4();
    void method5();
    void method6();
    void method7();
}
```

`MyInterfaceAdapter.java`

```java
public class MyInterfaceAdapter implements MyInterface{
    @Override
    public void method1() {}

    @Override
    public void method2() {}

    @Override
    public void method3() {}

    @Override
    public void method4() {}

    @Override
    public void method5() {}

    @Override
    public void method6() {}

    @Override
    public void method7() {}
}
```

`Main.java`

```java
public class Main {
    public static void main(String[] args) {
        new Service().method1();
    }
}

class Service extends MyInterfaceAdapter {

    @Override
    public void method1() {
        System.out.println("Hello World");
    }
}
```



만약 인터페이스에 메소드가 엄청 많은데, 내가 쓰고 싶은 몇개의 메소드만을 오버라이딩해서 쓰고 싶은 상황이 생기게 된다. (위 그림에서 MyInterface 메소드 7개를 선언하였는데, 인터페이스의 메소드가 많다고 가정하고 만든 메소드이다) 

그렇게 되면 이 Myinterface를 implements하는 측에서 모든 메소드를 구현하고 사용해야 하므로, 내가 쓰고 싶은 몇개 뿐만 아니라 쓰지 않을 메소드까지 구현해야 한다.

이때 MyInterfaceAdapter라는 클래스를 만들어서 MyInterface 인터페이스를 implements 하고 빈 메소드를 만들어 놓기만 하면 된다. 그리고 이 MyInterfaceAdapter를 사용할 클래스(위 코드에서는 Service 객체)에서 extends 하면 내가 쓰지않을 메소드를 구현할 필요 없이 필요로하는 메소드만 오버라이드하여 구현할 수 있게 된다. (Adapter의미 처럼 중간자 역할을 수행한다.)

이렇게 하면 굉장히 문제를 잘 해결한 듯 보이지만 역시나 불편한 점이 존재한다.

```java
class Service extends A implements MyInterface {}
```

이와 같이 Service클래스가 이미 A를 상속하고 있다면? 

자바는 다중 상속을 지원하지 않기 때문에 어쩔 수 없이 MyInterface를 다시 implements해야하고, 또 다시 모든 메소드를 구현해야 할 수 밖에 없게 된다.

**이때 디폴트 메소드의 진가를 발휘하게 된다.**

그냥 Myinterface 의 모든 메소드를 디폴트 메소드로 바꾸면 모든 문제를 해결할 수 있고, 더이상 Adapter 객체를 따로 만들 필요가 없어지게 된다.

`MyInterface.java`

```java
interface MyInterface {
    default void method1() {};
    default void method2() {};
    default void method3() {};
    default void method4() {};
    default void method5() {};
    default void method6() {};
    default void method7() {};
}
```

`Main.java`

```java
public class Main {
    public static void main(String[] args) {
        new Service().method1();
    }
}

class Service extends A implements MyInterface { 
    // MyInterface의 모든 메소드가 디폴트 메소드 이므로 내가 사용하고자하는 메소드만 오버라이드해서 사용 가능!
    @Override
    public void method1() {
        System.out.println("Hello World");
    }
}
```



디폴트 메소드의 활용 예를 살펴보자.

`Ability.java`

```java
// interface에 static 메소드도 구현가능 (인터페이스 이름으로 접근 가능)
public interface Ability {
    static void sayHello() {
        System.out.println("Hello World");
    }
}

interface Flyable {
    default void fly(){
        System.out.println("FLY");
    }
}

interface Swimmable {
    default void swim() {
        System.out.println("SWIM");
    }
}

interface Walkable {
    default void walk() {
        System.out.println("WALK");
    }
}
```

여기서 Ability 인터페이스를 보면 static 메소드를 구현한 것을 확인할 수 있는데, 즉 이 Ability는 sayHello라는 메소드를 제공해주게 되고 메소드가 한개있으므로 그냥 함수로 볼 수 있다. 그래서 **Ability 인터페이스는 sayHello라는 함수를 제공해준다** 라고 말할 수 있다.

우리가 말하는 메소드는 클래스 안에 종속된(구현된) 함수라고 표현을 한다. 

> 근데 자바 8 부터는 function이라고 구분해서 부르는 것들이 생겨나기 시작함 ex) functional interface

`Main.java`

```java
// default 메소드를 이용하면 아래에서 Duck, Swan 클래스에서
// 구현해 줄 필요가 없으므로 훨씬 깔끔하게 코드를 짤 수 있다.
class Duck implements Swimmable, Walkable {}

// Swan이 swim이 가능하다면 그냥 implements로 하나 넣어주기만 하면 아무런 구현 없이 기능이 확장됨
class Swan implements Flyable, Walkable,Swimmable {}

public class Main {
    public static void main(String[] args) {
        new Duck().swim();
        new Duck().walk();
        new Swan().fly();
        new Swan().swim();
        Ability.sayHello(); // static 메소드를 인터페이스 이름으로 접근
    }
}
```

👉result

```
SWIM
WALK
FLY
SWIM
Hello World
```

이처럼 디폴트 메소드로 미리 구현해놓고 Duck이나 Swan에 어울리는 인터페이스를 implements하여 기능을 추가할 수 있다.

---

### 함수형 인터페이스, 익명클래스, 람다 표현식이란?

자바 8에서 `함수`라는 단위가 계속 언급되고 있다.

거기서 `함수형 인터페이스` 라는 키워드가 등장하게 된다. 우선 이 함수형 인터페이스가 무엇인지 살펴보자.

```java
// 특별히 함수형 인터페이스라는 걸 알려주기 위한 애노테이션을 달아주기도 함
@FunctionalInterface
public interface MyRunnable {
    void run(); // 추상 메소드가 하나밖에 없는 메소드 == 함수형 인터페이스
}

// 얘는 추상 메소드가 2개이므로 함수형 인터페이스가 될 수 없다.
interface MyRunnable2 {
    void run1();
    void run2();
}

// 얘도 함수형 인터페이스다. 왜냐면 '추상' 메소드는 하나이기 때문이다.
// default, static method가 있는 것은 함수형 인터페이스의 결정과 상관 없다.
@FunctionalInterface
interface MyMap {
    void map();
    default void sayHello() {
        System.out.println("Hello World");
    }
    static void sayBye() {
        System.out.println("Bye World");
    }
}
```

즉 함수형 인터페이스는 default, static 메소드와 상관없이 추상메소드 딱 1개 만 가지고 있어야 함수형 인터페이스라고 할 수 있다.

> FunctionalInterface에 있는 추상메소드를 함수라고 부른다.



그럼 함수형 인터페이스를 왜 사용할까? 

함수형 인터페이스를 살펴보기전에 **익명 클래스**와 **람다 표현식**을 이해해야 한다.

우선 인터페이스로 인해 불편한 점을 살펴보자.

`MyRunnable.java`

```java
@FunctionalInterface
public interface MyRunnable {
    void run();
}
```

`MySupply.java`

```java
public interface MySupply {
    String supply();
}
```

`Main.java`

```java
package com.programmers.java.func;

class Greeting implements MySupply {

    @Override
    public String supply() {
        return "Hello World";
    }
}

class SayHello implements MyRunnable {

    @Override
    public void run() {
        System.out.println(new Greeting().supply());
    }
}

public class Main {
    public static void main(String[] args) {
        new SayHello().run();
    }
}

// 여기서 이 sayHello 클래스가 만들어진 이유는 MyRunnable은 인터페이스기 때문에
// 인스턴스를 new 키워드로 생성할 수 없기 때문에 sayHello 클래스가 있어야 비로소
// 인터페이스의 메소드를 구현하여 호출할 수 있게 된다.
```

MyRunnable과 MySupply 인터페이스를 구현할 임시 클래스를 만들어야 만 new 키워드를 이용한 인스턴스 생성이 가능하고 그제서야 인터페이스의 메소드를 사용할 수 있게 된다. 이로 인해 임시적인 클래스가 계속 늘어나게 되서 귀찮아진다..

그래서 그냥 인터페이스지만 new 키워드를 이용하여 사용할 수 없을까? 생각하게 된 것이 바로 익명 클래스의 등장 배경이다.



```java
public class Main {
    public static void main(String[] args) {
        
        
        //new class XXX implements MySupply {
        //    @Override
        //    public String supply() {
        //        return "Hello World";
        //    }
        //}.supply();
        // 원래는 이렇게 class를 만들어서 추상메소드를 구현하여 new 키워드를 이용했음.
    }
}
```



```java
public class Main {
    public static void main(String[] args) {
        
        // 인터페이스를 new 함으로써 임시 클래스 생성 안해도 됨
        // 이름 없는 클래스를 생성 => 익명 클래스
        // 굳이 class 생성없이 사용 가능하게 함 (어짜피 class 만드는건 뻔하니까)
        new MySupply() {
            @Override
            public String supply() {
                return "Hello World";
            }
        }.supply();
    }
}
```

이와 같이 익명 클래스를 만드는 아이디어를 통해 인터페이스를 new 키워드로 생성할 수 있다.

아래는 동작 코드를 작성한 것이다.

```java
public class Main {
    public static void main(String[] args) {
        
        MyRunnable r = new MyRunnable() {
            @Override
            public void run() {
                MySupply s = new MySupply() {
                    @Override
                    public String supply() {
                        return "Hello Hello"; 
                    }
                };
                System.out.println(s.supply());
            }
        };
        r.run();
    }
}
```

👉result

```
Hello World
```



익명 클래스를 만들 수 있다면, 익명 메소드도 만들 수 있지 않을까? 생각하게 됨.

```java
public class Main2 {
    public static void main(String[] args) {
        // 인스턴스가 만들어지면 구현부가 필요하니까 정의와 함께 구현부를 바로 넣어줌
        MyRunnable r1 = new MyRunnable() {
            // Override 하는 부분부터 뒤쪽은 너무 뻔하게 등장해야 하는 것들임.
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        // 위 코드는 '익명 클래스'를 사용한 거다.
        
        // 위 코드를 아래와 같이 간단하게 만들 수 있다. (익명 메소드를 사용한 것임)
        // 익명 메소드를 사용해서 표현하는 방법 : 람다 표현식
        MyRunnable r2 = () -> {
            System.out.println("Hello");
        }
        
        // MyRunnable r2 = () -> System.out.println("Hello"); 이렇게 한줄 밖에 없으면 {} 중괄호 까지도 생략 가능. 
        // MyRunnable r2 = () -> (return) "Hello"; 이처럼 만약 MyRunnable 에 있는 추상 메서드의 반환 타입이 있으면 return을 해줘야하는데, 이 또한 한줄이면 중괄호를 생략했듯이 return 또한 생략할 수 있다.
        
    }
}
```

여기서 MyRunnable은 함수형 인터페이스 이므로 어짜피 구현해야할 추상 메서드가 1개인 것은 뻔하다.

현재 위 코드는 r1, r2 동일한데, @Override, public, void, run 이 모든것들은 무조건 나와야 하는 것들이기 때문에 생략이 가능하다. (이게 가능한 이유는 추상메소드가 1개인 함수형 인터페이스 이기 때문에 가능) 

생략하고 나면 ()과 {}이 남게 된다.

() : 인자 받는 부분 

 {} : 구현 몸덩어리 가 남게 되는데 이들은 구분할 필요가 있으므로 -> 화살표를 통해 구분해 준다.

여기서 r2처럼 구현 된 것을 **람다 표현식** 이라고 한다.



만약 MyRunnable2 라는 인터페이스가 있고, 추상메서드가 void run1(), void run2(), 총 2개 있다고 해보자.

그럼 이 람다 표현식을 사용할 수 없다.

```java
MyRunnable2 r3 = () -> {};
```

여기서 run1 메소드를 이용하여 인스턴스를 만든것인지 run2 메소드를 이용하여 인스턴스를 만든 것인지 알 방법이 없기 때문이다.

> 즉, 함수형 인터페이스의 특성으로 인해 익명클래스, 익명메소드의 뻔한 부분을 생략시킬 수 있게되고, 최종적으로 생략한 형태를 람다표현식이라고 부르게 되는 것이다. ( 이 흐름을 이해하는게 굉장히 중요하다. )



앞서 익명 클래스로 작성한 코드를 아래와 같이 확 줄일 수 있다.

```java
public class Main2 {
    public static void main(String[] args) {
        MyRunnable r = () -> {
            MySupply s = () -> "Hello Hello";
            System.out.println(s.supply())''
        };
        r.run();
    }
}
```

👉result

```
Hello Hello
```

**결국 코드를 깔끔하게 작성하기 위해 함수형 인터페이스가 필요했고, 함수형 인터페이스의 특성으로 인해 익명클래스와 익명메소드의 뻔한 코드를 삭제하고 람다표현식으로 깔끔하게 표현할 수 있게 된 것이다**

---

## 람다 표현식 연습

`MyConsumer.java`

```java
@FunctionalInterface
public interface MyConsumer {
    void consume(int i);
}
```

`MyMapper.java`

```java
@FunctionalInterface
public interface MyMapper {
    int map(String s);
}
```

`MyRunnable.java`

```java
@FunctionalInterface
public interface MyRunnable {
    void run();
}
```

`MySupplier.java`

```java
@FunctionalInterface
public interface MySupplier {
    String supply();
}
```

`Main.java`

```java
public class Main {
    public static void main(String[] args) {
        MySupplier s = () -> "Hello World";

        MyMapper m = (str) -> str.length();
        //MyMapper m = String::length; // 메소드 레퍼런스

        MyConsumer c = (i) -> System.out.println(i);
        //MyConsumer c = System.out::println; // 메소드 레퍼런스
        MyRunnable r = () -> c.consume(m.map(s.supply()));

        r.run();
    }
}
```

여기서 인텔리제이가 노란줄을 보여주는데, 이때 alt + intsert를 이용하면

메소드 레퍼런스가 적용된다.

메소드 레퍼런스는 입력 받은 값이 **변경 없이** 바로 사용할 때 사용 가능하다.



이 메소드 레퍼런스를 쓰는 이유는 들어오는 인풋 값이 변경없이 바로 사용되는데, 코드를 공유하는 상황이 오면 **작성자**가 인풋 값을 변경없이 바로 사용하는 것을 **강조**했다고 내포할 수 있는 의미에서 메소드 레퍼런스를 사용하게 된다. (입력 값을 변경하지 말라는 표현 방식이다.)



위 예제에서 MySupplier가 현재는 String을 반환하는데, 만약 다른 형으로 타입을 반환하고 싶다면? 그때마다 매번 새로운 인스턴스를 만들어야 할까?

이때 사용하는것이 **제네릭** 이다.

제네릭을 사용하여 아래와 같이 코드를 변경할 수 있다.

(제네릭은 참조형 타입만 사용가능)

`MyConsumer.java`

```java
@FunctionalInterface
public interface MyConsumer<T> {
    void consume(T t);
}
```

`MyMapper.java`

```java
@FunctionalInterface
public interface MyMapper<IN, OUT> {
    OUT map(IN s);
}
```

`MySupplier.java`

```java
@FunctionalInterface
public interface MySupplier<T> {
    T supply(); // 제네릭을 이용하여 T라는 타입을 사용
}
```

`Main.java`

```java
public class Main {
    public static void main(String[] args) {
        MySupplier<String> s = () -> "Hello World";

        MyMapper<String, Integer> m = String::length;
       
        // 제네릭을 이용하면 아래와 같이 타입을 변경하여 메서드를 바꿀 수 있다.
        MyMapper<Integer, Integer> m2 = i -> i*i;
        MyMapper<Integer, String> m3 = Integer::toHexString;

        MyConsumer<String> c = System.out::println;
        
        MyRunnable r = () -> 
            c.consume(
                		m3.map(
                        		m2.map(
                                		m.map(
                                        		s.supply()
                                		)
                        		)
                		) 
        		);
            
        r.run();

    }
}
```

👉result

```
79
```

Hello World 길이가 11이고 이를 제곱한 121, 그리고 이를 16진수로 바꾸었으므로

결과는 79(16) 이 된다.

---

### 람다 표현식(함수형 인터페이스)을 이용한 또다른 예



위에서 우리가 MyConsumer, MyMapper, MyRunnable, MySupplier같은 함수형 인터페이스를 만들어 봤는데, 자바 8에서 이를 미리 만들어 두었음.

java 1.8 이상부터 java.util.function 안에 있는 인터페이스들이 함수형 인터페이스로 되어 있다.

```java
public class Main2 {
    public static void main(String[] args) {
        // 여기가 호스트다.
        new Main2().loop(10, System.out::println);
    }

    void loop(int n, MyConsumer<Integer> consumer) {
        for(int i = 0; i< n; i++) {
            // 반복문을 돌 건데, i를 입력으로 줘서 뭔가를 동작 시켜야 함 == 입력은 있고, 출력은 따로 없어도 된다.
            // 즉 MyConsumer의 메서드를 사용하면 됨.
            consumer.consume(i);
        }
    }
}

```

👉result

```
0
1
2
3
4
5
6
7
8
9
```



이 코드에서 loop 메소드가 만약 인자로 n만 받고 n까지의 합을 더하는 로직을 짜게 된다면 그냥 이 loop 메소드가 `기능`을 수행하게 된다.

근데 이 loop 함수안에서 자체적인 `기능`을 수행하지 않고 수행해야 할 것을 외부로 부터 전달받게 하면 좋다.

구체적인 사항에 대해서 내가 처리하지 않고, 뒷쪽으로 넘겨서 최종적으로 호스트가 그 결정을 하게 만드는 것이다. 

결국, 이 loop는 '나 (loop)는 루프(for)를 도는 것에만 관심이 있고(루프를 도는 작업만 처리할 거고) 그 외에 다른 작업은, 다른쪽으로 넘겨줄 테니까, 나를 호출하는 쪽에서 정하라고 호스트 코드쪽으로 넘긴다.

이렇게 하면 loop 라는 메소드가 본연의 기능을 만족하면서도, 호스트 코드는 자신이 원하는 인자를 넣어서 원하는 것을 얻을 수 있다. (이게 앞에서 설명한 DI와 같은 원리)

즉, 구체적으로 어떤 일을 할지는 나를 호출하는 사람이 안다. 이런 경우에 functional interface를 사용할 수 있음.



또 다른 함수형 인터페이스 예를 보자.

```java
public class Main2 {
    public static void main(String[] args) {
        // 여기가 호스트다.
        new Main2().filteredNumbers(30,
                i -> i%2 == 0,
                System.out::println
        );
    }
    
    void filteredNumbers (int max, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i=0; i<max; i++) {
            if(p.test(i)) c.accept(i);
        }
    }
}
```

👉result

```
0
2
4
6
8
10
12
14
16
18
20
22
24
26
28
```

filteredNumbers는 홀수를 거른다 던가, 짝수를 거른다던가 하는 기능이 없지만 그런 기능을 인자로 함수형 인터페이스를 받아서 수행할 수 있게 된다. (호출하는 측에서, 즉 외부에서 그 기능을 수행하게 된다) 이런 상황에서 함수형 인터페이스를 사용한다!