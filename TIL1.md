# TIL1

드디어 데브코스의 첫날 공부가 시작되었다.

간단히 자바 개발환경을 위한 셋팅을 진행했다.

이때 자바의 **실행환경**과 **개발환경**의 차이점을 이해할 수 있었다.

자바를 돌리기 위해서는 JVM이 필요한데, 이때 실행 환경은 JRE를 뜻하고, 실행하기위한 명령어로 java 클래스이름 로 쓰면 된다.

개발 환경은 JRE와 개발 툴을 합친 것으로, JDK를 뜻하고, 컴파일을 위한 명령어로 javac 클래스이름.java 로 쓰면 된다.

이렇게 명령어로 하나씩 실행, 컴파일하지 않고 자동으로 빌드해주는 툴이 3가지 있다.

Ant, Maven, Gradle이 있는데, maven은 자바언어에 특화되어있고, gradle은 여러가지 언어,환경을 제공해주고 maven보다 더 간결한 문법을 제공하는 등등 여러가지 조건으로 최근에는 gradle을 많이 쓰는 것을 알게 되었다.

gradle을 사용하기 위해 git bash를 이용하여 설치하려고 했는데 gradle 명령어가 먹지를 않았다. 이 명령어를 등록하기 위해 구글링 하면서 따로 블로그에 정리해 두었다. (https://engineer-diary.tistory.com/51?category=979146)

기본적인 셋팅을 완료하고 기본적인 자바 관련 규칙을 공부하였다.

자바의 코딩 컨벤션에 의하면 주로 camel 표기법을 쓴다는 것을 알게 되었다. 

또 자바언어는 파이썬 처럼 들여쓰기를 하는 것은 아니지만 깔끔한 코드를 작성하기 위해서 들여쓰기를 해야하는데, 사용하는 개발 환경에 따라서 들여쓰기를 tab, 띄어쓰기를 섞어서 사용하면 일치가 되지 않는 문제가 생기는 것을 알게 되었다. 따라서 둘중 하나만 사용해야 겠다고 생각했다. (개인적으로 tab이 편한데 다른 개발자들은 어떤걸 선호하는지 모르겠다.)

또 자바의 String 객체를 잘 이해할 필요가 있다. 그것에 관해서는 StringBuffer와 StringBuilder의 차이에 관한 포스팅을 등록하였다. (https://engineer-diary.tistory.com/55)

또 모든 객체의 최고 조상은 Object 객체인데, 이 객체의 메소드를 잘 아는 것이 중요했다. 특히 equals()와 hashCode()가 객체마다 따로 오버라이딩 하여 주소값 비교가 아닌 인스턴스 변수를 비교하여 처리해야 하는 점을 알 수 있었다.

기본적인 내용이었지만 애매하게 알고 있던 내용들과 몰랐던 내용들을 잘 정리해야겠다.

---

## Article 

[원본 보기 : https://engineer-diary.tistory.com/51?category=979146, https://engineer-diary.tistory.com/51?category=979146]

### 윈도우 git bash에서 zip,tree 명령어 사용하기

git bash를 이용하여 zip 명령어를 이용하면 command not found라고 뜬다. 

이때는 `C:\Program Files\Git\usr\bin` 경로에 실행파일을 붙여 넣어줘야한다.

우선 https://sourceforge.net/projects/gnuwin32/files/ 로 이동한다.

![image](https://user-images.githubusercontent.com/52458039/127837350-10945d1e-1bd5-4930-916a-c6959a6bb48d.png)

 저 zip 폴더에서 `zip-3.0-bin.zip` 파일을 다운받는다.

압축을 푼다음 zip.exe 실행파일을 `C:\Program Files\Git\usr\bin` 경로에 붙여넣어주면 zip 명령어를 사용할 수 있게 된다.

위와 비슷한 방식으로 tree 명령어 또한 사용할 수 있다.

[http://gnuwin32.sourceforge.net/packages/tree.htm﻿](http://gnuwin32.sourceforge.net/packages/tree.htm)

![image](https://blog.kakaocdn.net/dn/sgyvB/btra8zEpJdl/voOODK5JLYRy8AMthsEhcK/img.png)

Binaries 폴더의 tree.exe 파일을 `C:\Program Files\Git\usr\bin` 경로에 붙여주면 된다!

![image](https://blog.kakaocdn.net/dn/C5ZkI/btra9W68PgE/qPvRgkjkqE2exXEeLjfvsK/img.png)

잘 작동한다!

---

### 윈도우 git bash에서 gradle 빌드툴 설치하기

윈도우의 git bash에서는 기본적으로 gradle 명령어가 적용되어 있지 않다. 따라서 gradle을 설치하는 방법을 살펴보자

우선 jdk가 설치되어 있어야하고, SDKMAN을 사용하여 설치하는것을 권장한다고 함.

1. **SDKMAN 설치 (http://sdkman.io/install.html)**

```
$ curl -s "https://get.sdkman.io"|bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
```

sdk help를 통해 도움말이 나오면 정상적으로 sdkman이 설치 된 것이다.

2. **gradle 설치(http://sdkman.io/usage.html)**

```
$ sdk install gradle
```

이후 sdk list gradle로 현재 버전과 설치 버전들을 확인할 수 있다.

> 참고 : 
>
> sdk install gradle <version> 을 통해 특정 버전을 설치할 수 있고 
>
> sdk default gradle <version>을 통해 기본 버전 설정을 할 수 있다.

3. gradle 환경 변수 등록

~./bashrc에서 해당 내용을 export 해준다.

```
export GRADLE_HOME=<GRADLE_HOME>
export PATH=$GRADLE_HOME/bin:$PATH
```

> vi ~/.bashrc 를 통해 빔 에디터를 사용하여 수정후 등록 가능

저장후 바로 gradle 환경변수가 등록되지 않으므로 source ~/.bashrc 를 통해 변경된 설정을 재 설정 해준다.

4. gradle 설치 확인

```
gradle -v
```

정보가 잘 뜨면 성공적으로 확인 가능하다.

---

### gradle 빌드 툴을 이용하여 프로그램 실행하기

gradle의 build, run 이런것들은

gradle이 수행하는 명령단위, task라고함

그래서 gradle은 task를 수행함으로써 내용이 수행됨. 

build.gradle에 이 task가 등록이 되어있다.

![image](https://user-images.githubusercontent.com/52458039/127821350-a9385309-0ea7-417c-8531-8b0146bc476d.png)

근데 build나 run에 대한 task는 기록되어있지 않은데, 기본적으로 gradle init할때 우리는 java로 하기로 했으니 그것에 대한 build, run은 plugins을 통해 다 세팅되게 된다.

```
gradle tasks 
```

이 명령어를 통해 gradle의 task들을 전부 확인할 수 있다.

![image](https://user-images.githubusercontent.com/52458039/127836654-a9fd76bc-408d-4854-9322-57975859eac9.png)

**인텔리제이 단축키**

alt + enter : 빨간줄 생길때 자동완성해줌

alt + 1 : 소스코드에서 왼쪽 프로젝트 창으로 커서가 이동함. esc 누르면 다시 소스코드로 넘어옴

ctrl + n : 해당 폴더에서 클래스 만들기 (윈도우에서는 안되는 듯?)

shift 2번 : 폴더, 파일이 많을때 찾고자하는 파일이름 검색가능



alt + up/down : 드래그 영역 범위 지정 (윈도우에서 안되는듯?)



ctrl + alt + l : 코드 리포맷팅

shift + ctrl + alt + t : 리팩토링 메뉴

(rename, method 생성, 변수,상수 빼내서 만들기 등등)

ctrl + shift + a : 모든 명령어 외울 필요없이 검색 가능 (ex refactor) 즉, 어디 메뉴에 있는지 모를때, 그냥 이거 쓰면 됨