# TIL2

오늘은 객체지향관점에서의 Java를 공부하였다.

기본적으로 객체지향의 특성에는 

\1. 캡슐화 2. 상속 3. 추상화 4. 다형성

이 있다.

여기서 새롭게 안 사실은 접근 지정자에 friendly 키워드를 통해 같은 패키지 내에서 접근 가능하다는 것을 알게 되었다.

또한 상속을 할때 부모의 멤버 변수가 private일 경우 상속 받은 자손은 그 변수를 사용할 수 없다는 사실도 알게 되었다.(이거는 접근지정자 private의 뜻을 정확히 알지 못해서 잘못 알고 있었다. 이번 기회에 오개념을 잡을 수 있었다.)

또, 상속을 하는 의미에 대해 다시 한번 알 수 있었다. 흔히 상속을 하는 이유라고 하면 **공통된 기능을 여러 객체에서 사용하고 싶을때** 상속을 한다, 라고 이해하고 있다. 하지만 이것은 오해다. 이러한 이유를 가지고 프로그래밍을 하게 된다면 좋은 객체 지향의 설계 원칙을 지키지 못하게 된다. 

상속을 하는 이유는 상속을 통해 부모를 더 추상화 시키고, 자손을 더 구체화 시키 위함이다. 즉 객체 간의 관계에서 상위에 있을 수록 하위 객체보다 추상적이여야 한다는 것이다. 여기서 자연스럽게 인터페이스 개념과 다형성이 등장하게 된다.

인터페이스는 포스팅을 통해 정리하였다. (다형성을 쓰는 이유도 정리되어 있음) (https://engineer-diary.tistory.com/56?category=999296)

객체지향 프로그래밍을 하게 되면 객체를 어떻게 나누고 서로의 연관관계를 그림으로 그려서 설명할 줄 알아야 한다. 이를 UML 이라고 하는데, 앞으로 객체지향 프로그래밍을 하게 된다면 반드시 이 그림을 잘 그려놔야 겠다고 생각했다. 관련 Tool로는 학부때도 자주 쓴 [https://draw.io](https://draw.io/) 를 써야 겠다고 생각했다.

그리고 객체를 나누고 연관을 어떻게 **잘** 지을 것인가? 의 문제로 자연스럽게 넘어오게 된다.

여기서 객체지향 설계를 하는 5가지 원칙이 등장하는데, 이 5가지만으로는 실제 적용이 어려웠기 때문에 사람들이 공통적으로 생각한 패턴을 정리한 것이 **디자인 패턴** 이다. GoF 디자인 패턴 책에서 23가지 패턴을 설명해 두었는데, 관련 내용을 포스팅으로 정리하였다. (https://engineer-diary.tistory.com/59?category=999296)

아직까지 코딩이 아닌 이론적인 부분을 보다보니 끄덕이면서 한편으로는 갸우뚱한 것 같다. 앞으로 이런 것들을 하나씩 채워 갈 생각을 하니 벌써 설렌다.

오늘은 7시부터 진유림 님의 Git/GitHub 강의가 진행 되었다. 지금까지 나는 항상 git bash를 이용해서 주로 add, commit, push, checkout, branch만을 사용했던 것 같다. 하지만 이번 강의에서는 bash 뿐만 아니라 소스트리를 이용해서 GUI로 훨씬 쉽게 이해할 수 있었다. 특히 merge가 일어날때 발생하는 경우의수를 알려주는 등, conflict 또한 쉽게 풀어내는 모습을 보며 몰랐던 내용도 확실하게 구체적으로 정리할 수 있어서 좋았다. 또 rebase, revert, cherry-pick 같은 익숙하지 않은 것들 또한 소스트리와 함께 실습하면서 이해를 쉽게 할 수 있었다.

좋은 강의 해주신 진유림 님 너무 감사합니다.

---

## Article

[원본 보기:https://engineer-diary.tistory.com/56?category=999296]

### 인터페이스란?

자바에서 인터페이스는 추상클래스처럼 추상 메서드를 갖는다. 추상 클래스와 차이점은 멤버 변수를 구성원으로 가질 수 없고 메서드는 구현되어 있지 않아야 한다.

보통 인터페이스를 밑그림만 그려진 '기본 설계도' 라고 한다.

그럼 도대체 이러한 특성을 가지는 인터페이스를 왜 사용하는 걸까?

1. **구현을 강제 시킨다.**

```java
interface Login {
    void login();
}

public class KakaoLogin implements Login{
    @Override 
    public void login() {
        System.out.println("카카오로 로그인 합니다.");
    }
}
```

위 코드와 같이 Login을 implements하게 될 경우 Login의 메서드인 login을 반드시 KakaoLogin에서 구현해 줘야한다.

2. **다형성을 제공한다.**

```java
interface Login {
    void login();
}

class KakaoLogin implements Login{
    @Override 
    public void login() {
        System.out.println("카카오로 로그인 합니다.");
    }
}

public class Main {
    public static void main(String[] args) {
        Login user = new KakaoLogin();
        user.login();
    }
}
```

위의 main 메서드에서 KakaoLogin을 인스턴스로 생성하였지만 참조변수 타입을 Login으로 설정할 수 있다. 이렇게 되면 Login 인터페이스에 있는 메서드만 사용할 수 있게 된다.

3. **객체간의 결합도를 낮춰 준다.**

결국 1, 2로 인해 객체간의 결합도를 낮출 수 있게 된다.

`Main.java`

```java
public class Main {
    public static void main(String[] args) {
        UserService s = new UserService(new KakaoLogin());
        s.login();
    }
}
```

`KakaoLogin.java`

```java
public class KakaoLogin implements Login{
    @Override
    public void login() {
        System.out.println("카카오로 로그인 합니다.");
    }
}
```

`NaverLogin.java`

```java
public class NaverLogin implements Login{
    @Override
    public void login() {
        System.out.println("네이버로 로그인 합니다.");
    }
}
```

`Login.java`

```java
interface Login {
    void login();
}
```

`UserService.java`

```java
package com.programmers.java.poly;

public class UserService implements Login{

    // 로그인에 의존한다.
    private Login login;

    // 의존성을 외부에 맡긴다면, 의존도를 낮춘다.
    // 결합성 : UserService가 KakaoLogin이나 NaverLogin같은 구상체와 결합하게 되면 결합성이 강하다.
    // 반면 UserService가 추상체인 Login과 결합하게 되면 결합도가 낮아졌다고 얘기한다.
    // 아래 생성자를 통해 의존성을 외부로 부터 전달 받는다면 => 의존성을 주입 받았다고 표현한다.
    // 이것을 의존성 주입, Dependency Injection, DI 라고 한다.
    public UserService(Login login) {
        this.login = login;
    }

    @Override
    public void login() {
         login.login();
    }
}

```

UserService 클래스 입장에서는 멤버변수인 login에 구현체가 들어와야 메서드를 수행할 수 있다. (이를 로그인에 의존한다고 말한다.)

만약 멤버 변수에서 바로 인스턴스를 생성(예를들어 카카오 로그인)한다면 이 UserService는 카카오 로그인밖에 수행하지 못하게 된다.

이를 UserService 내부에서 처리하는게 아니라 외부에서 처리하도록 만든다. 외부에서 UserService 생성자를 통해 멤버변수인 login에 구상체를 결합시켜 주게 된다 (외부에서 결합시켜 주므로 이를 의존성 주입이라고 한다). 이때 카카오 로그인으로 할지, 네이버 로그인으로할지 결정만 해주면 된다.  

![image](https://blog.kakaocdn.net/dn/GjP4Q/btra331BrIb/6mIPvYi8q1iPEWanxnNiJk/img.png)

객체 관계를 다음과 같이 표현할 수 있고, Login 인터페이스가 실제 구상체의 **껍데기 역할**을 수행하게 되면서 결합도를 낮춰주는 효과를 가져다 준다.

이러한 인터페이스의 기본 효과로 인해 좋은 객체지향의 설계 5가지 원칙중 하나인 DIP(Dependency Inversion Principle)를 만족시킬 수 있게 된다. 즉, 쉽게 구상체를 갈아끼우며 필요에 따라 사용할 수 있게 된다.

이것이 인터페이스를 사용하는 이유이다.