# Transaction Isolation Level

![Untitled](./images/Untitled.png)

## Transaction이란

데이터베이스에서 하나의 논리적 작업 단위를 구성하는 일련의 연산들의 집합을 **트랜잭션**이라고 한다.

트랜잭션은 **_A.C.I.D_** 성질이라고 하는 다음의 네 가지 성질로 설명된다.

- **A**tomicity
  - 트랜잭션의 모든 연산들이 정상적으로 수행 완료되거나 아니면 전혀 어떠한 연산도 수행되지 않은 상태를 보장 (0 or 1)
- **C**onsistency
  - 고립된 트랜잭션의 수행이 데이터베이스의 **일관성**을 보존
  - 랜잭션 수행 전후의 데이터베이스 상태는 각각 일관성이 보장되는 서로 다른 상태
- **I**solation
  - 여러 트랜잭션이 동시에 수행되더라도 각각의 트랜잭션은 다른 트랜잭션의 수행에 영향을 받지 않고 독립적으로 수행되어야 한다.
- **D**urability
  - 트랜잭션이 성공적으로 완료되어 커밋되고 나면, 해당 트랜잭션에 의한 모든 변경은 향후에 어떤 소프트웨어나 하드웨어 장애가 발생되더라도 보존되어야 한다.

## Isolation Level

먼저 **isolation level**이란 트랜잭션에서 일관성이 없는 데이터를 허용하도록 하는 수준을 의미한다.

**ANSI**(미국 국가표준 협회)에서 작성된 SQL-92 표준은 **네 종류**의 Isolation Level을 정의하고 있으며 SQL Server 7.0은 이 표준을 준수합니다.

**Isolation Level을 조정하는 경우** 동시성이 증가되는데 반해 데이터의 무결성에 문제가 발생할 수 있거나, 데이터의 무결성을 완벽하게 유지하는 데 반하여 동시성이 떨어질 수 있으므로 그 기능을 완벽하게 이해한 후에 사용해야 합니다.

![Untitled](./images/Untitled%201.png)

isolation level에 따른 부작용, isolation level이 낮을 수록 부작용이 커진다.

---

## Isolation Level에 따라 나타나는 현상 3가지

먼저 Isolation Level에 따라 나타나는 현상을 보고 가겠습니다.

### 1. Dirty Read

어떤 트랜잭션에서 아직 실행이 끝난지 않은 다른 트랜잭션에 의한 변경 사항을 보게 되는 되는 경우를 **dirty read** 라고 합니다.

만약 원래 트랜잭션에서 그 변경 사항을 **롤백(`rollback`)**하면 그 데이터를 읽은 트랜잭션은 **dirty data**를 가지고 있다고 말합니다.

### 2. Repeatable Read

어떤 트랜잭션에서 같은 질의를 사용했을 때 **질의(`select`)**를 아무리 여러번 해도 그리고 다른 트랜잭션에서 아무리 여러 번 그 행을 **변경(`update`)**해도 항상 같은 데이터만 읽어드리는 경우를 **repeatable read** 라고 합니다.

즉 **repeatable read** 가 요구되는 트랜잭션에서는 다른 트랜잭션에 의한 변경 사항을 볼 수가 없습니다. 그러한 변경 사항을 보고 싶으면 어플리케이션에서 트랜잭션을 새로 시작해야 합니다.

### 3. Phantom Read

**phantom read** 는 다른 트랜잭션에 의한 **변경(`update`)** 사항으로 인해 현재 사용 중인 트랜잭션의 `WHERE` 절의 조건에 맞는 새로운 행이 생길 수 있는 경우에 관한 것입니다.

가장 쉬운 표현으로 내가 딱히 새로 만들거나 지운 것도 아닌데, 없던 놈이 보이고, 있던 놈이 사라지는 것이다.

---

## Isolation level 종류 (ANSI/ISO SQL Standard)

### 1. read uncommitted

![Untitled](./images/Untitled%202.png)

한 **transaction**(T1)이 데이터베이스 **row**를 이미 `update`은 하고 `commit`은 하지 않은 경우 다른 **transaction**(T2)은 해당 **row**를 `update`할 수 없다.

`SELECT` 문장을 수행하는 경우 해당 데이터에 **Shared Lock**이 걸리지 않는 level 이다.

이렇게 하면

- `update`가 손실되지 않도록 보호되지만,
- **Dirty Data**의 **질의**(`select`)는 허용한다.

### 2. read committed

![Untitled](./images/Untitled%203.png)

`SELECT` 문장이 수행되는 동안 해당 데이터에 **Shared Lock**이 걸린다.

**Read Committed Isolation level**은 `commit`되지 않은 **트랜젝션**(T1)이 **쓴**(`update`) row를 **다른 트랜젝션**(T2)가 쓰거(`updtae`)나 읽을(`select`) 수 있도록 허용하지 않는다.

**쓰고**(`update`) 있는 **트랜잭션**(T1)이 동일한 `row`에 **질의**(`select`)하는 **트랜잭션**(T2)이 액세스하는 것을 `commit` 될 때 까지 차단한다.

이렇게 되면 트랜젝션(T2)는 손실된 업데이트 및 dirty read로 부터 보호를 받을 수 있다.

### 3. repeatable read

![Untitled](./images/Untitled%204.png)

**Repeatable Read Isolation level**은 Row에서 데이터를 **질의**(`select`)하는 **트랜잭션**(T1)이 동일한 row를 **업데이트**(`update`)하려는 **트랜잭션**(T1)을 차단한다.

### 4. serializable

이 격리 모드는 다른 트랜잭션에서 데이터를 삽입하거나 읽지 못하도록 테이블 전체를 잠근다.

**Serializable Isolation**는 사용 가능한 모든 레벨 중에서 가장 엄격하고 비용이 많이 든다. 전체 테이블이 잠기면 다른 트랜잭션이 대기해야 할 확률이 매우 크다.

이 경우 테이블이 잠기기 때문에 `insert`도 불가능하다!

[Ansi Isolation Levels](https://www.notion.so/349485ff2a4f4969b107fac736271b5e)

Mysql 의 **InnoDB** 스토리지 엔진의 기본 Isolation Level이 **REPEATABLE-READ**.

### Isolation level은 왜 사용하는 겁니까?

> 당연히 성능이다. 예를 들어, 한 사용자가 어떠한 데이터를 수정하고 있는 경우 다른 사용자들이 그 데이터에 접근하는 것을 차단함으로써 완전한 데이터만을 사용자들에게 제공하게 됩니다.
> 또한, 많은 사용자들의 수정 작업으로 인하여 통계 자료를 작성할 수 없는 사용자를 위하여 읽기 작업을 수행할 수 있도록 Isolation Level을 변경할 수 있습니다.

하지만 자세한 사용 예시를 들어본 적이 없다 ㅠ

---

### 참고

- 트랜잭션 격리 이야기에서 팬텀 읽기 현상 : [https://postgresql.kr/blog/pg_phantom_read.html](https://postgresql.kr/blog/pg_phantom_read.html)
- Transaction Isolation Levels With Examples : [https://allaroundjava.com/transaction-isolation-levels/](https://allaroundjava.com/transaction-isolation-levels/)
- 데이터베이스 Transaction Isolation Level : [https://effectivesquid.tistory.com/entry/데이터베이스-Isolation-Level](https://effectivesquid.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-Isolation-Level)
