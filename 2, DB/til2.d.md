# TIL : 프로비저닝

- [x]  알게 된 내용
    - 프로비저닝
    - IaaS, SaaS, PaaS
- [x]  느낀 점
- [x]  REF

## 프로비저닝 (Provisioning)

- 사용자의 요구에 맞게 시스템 자원을 할당, 배치, 배포해 두었다가 필요 시 시스템을 즉시 사용할 수 있는 상태로 미리 준비해 두는 것을 말한다.
- 서버 자원 프로비저닝, OS 프로비저닝, 소프트웨어 프로비저닝, 스토리지 프로비저닝, 계정 프로비저닝 등이 있다.
- 수동으로 처리하는 '수동 프로비저닝'과 자동화 툴을 이용해 처리하는 '자동 프로비저닝'이 있다.
- 종류
    - 서버 자원 프로비저닝
        - 서버의 CPU, 메모리 등의 자원을 할당 또는 적절하게 배치하여 운영이 가능하도록 준비하는 것
    - OS 프로비저닝
        - OS를 서버에 설치하고 구성 작업을 해서 OS가 동작 가능하도록 준비해두는 것
    - 소프트웨어 프로비저닝
        - 소프트웨어(WAS, DBMS, 애플리케이션 등)을 시스템에 설치 배포하고 필요한 구성 셋팅 작업을 해서 실행 가능하도록 준비하는 것
    - 스토리지 프로비저닝
        - 낭비되거나 사용되지 않는 스토리지를 식별하고 공통 풀에서 옮긴 후 스토리지에 대한 요구가 접수 되면 관리자는 공통 풀에서 스토리지를 꺼내 사용 효율성을 높일 수 있는 인프라 구축 가능하도록 하는걸 스토리지 프로비저닝이라고 합니다.
    - 계정 프로비저닝
        - HR담당자와 IT관리자는 승인절차 밟은 후 e-mail ,그룹웨어 ,ERP등 다양한 어플리케이션에 필요한계정을 생성하거나 접근권한을 변경해주데, 이러한 일련의 과정을 계정 프로바이저닝

    ![TIL%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%87%E1%85%B5%E1%84%8C%E1%85%A5%E1%84%82%E1%85%B5%E1%86%BC%20e84424184f1c4f1ab7973289b0935265/Untitled.png](TIL%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%87%E1%85%B5%E1%84%8C%E1%85%A5%E1%84%82%E1%85%B5%E1%86%BC%20e84424184f1c4f1ab7973289b0935265/Untitled.png)

## IaaS, SaaS, PaaS

- 클라우드 컴퓨팅이 도입되면서 생긴 용어로 클라우드 이전에는 모두 사용자가 관리해야 했지만, 이제는 일정 부분을 클라우드에서 내려받는 형태가 많이 도입
- 사용자가 관리하고 얼마만큼 클라우드에서 제공하냐 따라 3가지로 나누어짐

![TIL%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%87%E1%85%B5%E1%84%8C%E1%85%A5%E1%84%82%E1%85%B5%E1%86%BC%20e84424184f1c4f1ab7973289b0935265/Untitled%201.png](TIL%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%87%E1%85%B5%E1%84%8C%E1%85%A5%E1%84%82%E1%85%B5%E1%86%BC%20e84424184f1c4f1ab7973289b0935265/Untitled%201.png)

- IaaS (Infrastructure as a Service)
    - 고객에게 서버, 네트워크, 스토리지를 가상화하여 제공
    - 일반적으로 일부의 OS가 지원 (리눅스나 윈도우)
    - 고객은 OS와 애플리케이션을 직접 관리
    - 아마존과 같은 AWS의 EC2의 VM을 제공 등
    - 장점
        - 고객은 가상 서버 하위의 레벨에 대해서는 고려할 필요 없음
        - 자유롭게 설정 가능
    - 단점
        - 가상 서버 하위의 레벨에 대해서는 전혀 고객이 접근하거나 컨트롤 할 수 없음
        - 가상 서버 하위의 레벨에 대해서 고려할 필요가 없는 사용자가 쓰기에 적합한 모델
    - 예시
        - AWS의 EC2
            - 물리적인 서버, 네트워크, 스토리지 등을 제공
            - OS 선택권을 고객에게 주고 고객이 설정하여 사용
            - Mosquitto등을 설치하여 MQTT등의 애플리케이션 사용 가능

- PaaS (Platform as a Service)
    - 개발자가 응용 프로그램을 작성할 수 있도록 플랫폼 및 환경을 제공하는 모델
    - 사용자는 애플리케이션 자체에만 집중 가능하여 빠르게 애플리케이션을 개발하고 서비스 배포 가능
    - node.js나 자바와 같은 런타임을 미리 깔아놓은 클라우드 서비스를 제공
    - MySQL이 설치되어 있는 클라우드 서비스 등
    - 장점
        - 이미 설치된 미들웨어 위에 코드만 돌리기 때문에 관리 편리
    - 단점
        - 애플리케이션이 플랫폼에 종속되어 개발
    - 예시
        - Heroku, Google App Engine, IBM Bluemix, OpenShift, SalesForce

- SaaS (Software as a Service)
    - 클라우드를 통해 제공되는 SW
    - 장점
        - 사용자는 웹만 접속하면 되기 때문에 사용하기 매우 쉽고, 최신 SW 업데이트를 빠르게 제공
    - 단점
        - 반드시 인터넷에 접속할 있어야 함
        - 외부의 데이터 노출 위험
    - 예시
        - 웹 메일, 구글 클라우드, 네이버 클라우드, MS 오피스 365 등이 설치된 OS 제공

## 느낀 점

- 클라우드 용어에서 빈번하게 사용되었던 용어인 "프로비저닝, IaaS/PaaS/SaaS등을 정리하여 익힘
- AWS에서 MySQL을 설치하면서 이 부분은 3부분 중 어디 부분일까라는 의문점을 해결
- IaaS를 공부하면서 나왔던 용어인 "오픈스택"의 경우도 용어정리가 필요
- 클라우드를 공부하면서 앞으로의 클라우드는 선택이 아니라 필수라고 느낌

## REF

- [https://jins-dev.tistory.com/entry/프로비저닝Provisioning-이란](https://jins-dev.tistory.com/entry/%ED%94%84%EB%A1%9C%EB%B9%84%EC%A0%80%EB%8B%9DProvisioning-%EC%9D%B4%EB%9E%80)
- [https://www.redhat.com/ko/topics/automation/what-is-provisioning](https://www.redhat.com/ko/topics/automation/what-is-provisioning)
- [https://docs.microsoft.com/ko-kr/azure/active-directory/cloud-sync/what-is-provisioning](https://docs.microsoft.com/ko-kr/azure/active-directory/cloud-sync/what-is-provisioning)
- [https://www.whatap.io/ko/blog/9/](https://www.whatap.io/ko/blog/9/)
- [https://wnsgml972.github.io/network/2018/08/14/network_cloud-computing/](https://wnsgml972.github.io/network/2018/08/14/network_cloud-computing/)