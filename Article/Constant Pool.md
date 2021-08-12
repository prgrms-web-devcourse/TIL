Constant pool은 리터럴 상수 값을 저장하는 곳이다.

``` java
  String a = "String Pool";
  String b = "String Pool";
  String c = new String("String Pool");

  System.out.println((a==b)?true:false); //true
  System.out.println((a==c)?true:false); //false
```

-   java에서 일반적으로 문자열을 비교할 때 == 연산을 사용하지 않고 .equals()를 사용하여 문자열이 일치하는지 비교한다.  
    그 이유는 java에서 == 연산은 객체의 주소값을 비교할 때 사용하기 때문이다.  
    하지만 예시 코드의 연산 결과는 true이다. 그 이유를 알기 위해서는 Constant Pool과 literal에 대해서 이해해야 한다.

String 변수를 생성하는 방법은 2가지가 있다.

```
  String a = "String Pool"; //literal
  String b = "String Pool"; //literal
  String c = new String("String Pool"); //new
```

**literal 방식으로 생성하면, Constant Pool 영역에 존재하게 된다.** 새로 문자열을 생성 시 내부적으로 intern() 메소드를 호출하고, 이 메소드는 Constant Pool에서 해당 문자열이 있는지 검색한다. 있다면 해당 문자열의 주소값을 반환, 없으면 새로운 값을 할당하여 해당 주소값을 반환한다.  
때문에 첫번째 예제에서는 같은 Constant를 가리키기 때문에 일치한 결과가 나온 것이다.

**new 방식으로 생성하게 되면 Heap 영역에 존재하게 된다. 때문에 new 연산자로 String 변수를 생성하는 경우 주소가 새롭게 할당되기 때문에 == 연산으로 주소값을 비교할 경우 false가 나온다.**

---

### 기본형(primitive type)과 참조형(reference type) / Call by value 과 Call by reference

reference는 c언어의 포인터와 비슷한 개념이다.

**reference형은 일반적인 변수들로 변수가 새롭게 정의되면 Heap 영역에 저장되어 새롭게 주소값을 할당받는다.**  
하지만 **primitive형은 stack메모리에 저장되고, 변수의 메모리공간에 변수가 가지는 값 자체를 저장한다.**  
primitive형 변수 : **boolean, byte, int, short, long, float, double, char**

-   int\[\] 형과 같이 배열이 될 경우 reference형으로 생성된다.

JAVA의 경우 함수에 전달되는 인자의 데이터 타입에 따라서 (primative type과 reference type) 함수 호출 방식이 달라진다.  
primative type의 경우 Call by value,  
reference type의 경우 Call by reference로 동작한다.

```
  static void swap(int a){
    int temp = x;
    x = y;
    y = temp;
  }

...

  int a = 10;
  int b = 20;

  swap(a, b);

  System.out.println("a = "+a+", b = "+b); //10, 20
```

위 함수를 실행했을 때의 결과는 a = 10, b = 20이다. int형은 primitive이기 때문에 주소에 접근하지 않고 연산하게 되서 swap 연산을 하는 함수를 호출한 후 다시 main에서 출력해도 값이 바뀌지 않고 그대로 출력된다.  
그렇다면 반대로 reference형 변수를 이용해서 연산할 경우는 어떻게될까?

```
Class CallByReference{
  int value;

  CallByReference(int value) {
    this.value = value;
  }

  public static void swap(CallByReference x, CallByReference y) {
    int temp = x.value;
    x.value = y.value;
    y.value = temp;
  }

  public static void main(String[] args) {
    CallByReference a = new CallByReference(10);
    CallByReference b = new CallByReference(20);
    swap(a, b);
    System.out.println("a = "+a+", b = "+b); //20, 10
  }
}
```

위의 예시 코드는 변수 a,b에 주소값을 저장하기 때문에 주소를 참조하여 데이터를 변경할 수 있다. 때문에 swap 연산 결과는 a = 20, b = 10이다. swap() 메서드에서 a,b는 서로 저장된 주소값을 바꾸어 저장하기 때문에 출력값이 바뀐 것이다.

추가로 한가지 예시를 더 살펴보자.

```
  static void sumTen(int a){
    a *= 10;
  }

  Integer a = 10;
  sumTen(a);
  System.out.println(a); //10
```

앞의 설명대로라면 윗 예제코드에서는 100이 나와야하지만 똑같이 10이 출력되었다. 그 이유는 무엇일까? 답은 이전에 String과 StringBuilder/StringBuffer의 차이점을 살펴봤을 때와 같은 곳에 있다. 이전에 String은 immutable 변수로서 Constant Pool 영역에 존재하기 때문에 값이 바뀌면 constant를 새롭게 생성한다는 것을 기억할 것이다. 마찬가지로 **Integer 타입 또한 immutable**하기 때문에 위와같은 연산에도 똑같이 10이 출력되는 것이다.  
reference형과 primitive형의 차이로 인해 발생하는 차이점과 immutable과 mutable의 차이로 인해 발생하는 차이점 모두 알아두는 것이 좋을 것 같다.

** AtomicInteger를 Integer의 사용하면 이런 문제를 어느정도 해결할 수 있을 것 같다. 추가로 정리하면 좋을 것 같다
