## 0. Java 설치하기

학교를 졸업하기 전부터 수업과 개인 공부에 사용하는 Java 버전이 다른 경우가 많아 여러 버전을 사용하는 방법을 찾았었다. 기존에는 [jenv]([GitHub - jenv/jenv: Manage your Java environment](https://github.com/jenv/jenv))를 사용해서 버전을 필요할 때 마다 변경했었지만, 아래 포스팅의 과정처럼 하는 것이 조금 더 번거롭기는 하지만 내가 어떤 목적으로 어떤 버전을 사용하는지가 머릿속에 좀 더 확실히  남는 것 같다.

**[mac에 여러 버전의 Java를 설치하고 사용하기]([mac에 여러 버전의 Java를 설치하고 사용하기](https://praetoriani.tistory.com/40))**

## 1. IntelliJ Shortcuts

shortcut은 알면 알수록, 잘 사용하면 할수록 급격하게 코딩이 편해지는 것 같다.
개인적으로는 Extract Variable [⌥ + ⌘ + V]과 Extract Method [⌥ + ⌘ + M]이 두 개가 참 편한 것 같다.
앞으로는 Refactor this [⌃ + T]를 사용하면서 인텔리제이에서 제공하는 리팩터링 기능들을 최대한 잘 활용하는 법을 익히면 도움이 많이 될 것 같다.

## 2. Constant Pool

맨 처음 Java를 배울 때의 습관때문에 String 끼리의 비교에는 항상 equals() 메서드만 사용해왔다. 때문에 Constant Pool 이라는 단어를 머리 속에서 지워놓고 있었던 것 같다.
기초에 소흘해진다는게 별게 아니라 바로 이런 것들이 쌓이고 쌓이는 건가보다 하는 생각이 들었다.