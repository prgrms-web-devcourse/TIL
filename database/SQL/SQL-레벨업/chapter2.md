# SQL Study

# 2장 - SQL 기초

RDB는 데이터를 관계라는 형식으로 저장하며 이를 테이블이라고 불러 구현을 합니다.

SQL은 테이블을 검색해서 데이터를 검색하거나 갱신할 때 사용하는 언어이며 직관적으로 사용할 수 있습니다.

하지만 복잡한 작업을 수행할때 위의 장점을 제대로 사용할 수 없으며, 성능이 따라오지 못합니다. 2장에서는 일단 기초적인 SQL 구문에 대해 설명합니다.

## SELECT 구

Query(질의)를 통해 데이터가 저장되어 있는 테이블에서 필요한 데이터를 뽑아내는 것.

```java
SELECT [필드] FROM [테이블 이름] WHERE [조건];
```

1. SELECT 구
   - 테이블이 갖고 있는 필드를 지정
2. FROM 구
   - 데이터를 선택할 대상 테이블을 지정

## WHERE 구

- 레코드를 선택할 때 추가적인 조건을 지정
- 연산
  - `=` : ~와 같음
  - `<>` : ~와 같지 않음
  - `>=` : ~이상
  - `>` : ~보다 큼
  - `<=` : ~이하
  - `<` : ~보다 작음
- WHERE 구는 거대한 벤다이어 그램
  - WHERE 구는 복잡한 조건을 지정할 때 벤다이어그램을 그려보면 쉽게 이해할 수 있다고 책에서 기술...

1. IN 또는 OR 조건

   ```sql
   SELECT name, address FROM Address WHERE address='서울시' OR address='대구시';

   --- 위와 동일 ---

   SELECT name, address FROM Address WHERE address IN ('서울시', '대구시');
   ```

   - IN으로 짜면 더 깔끔

2. NULL
   - 위키: 데이터베이스 내의 데이터 값이 존재하지 않는다는 것을 지시하는데 사용되는 특별한 표시어
   - 연산
     - `IS NULL`
     - `IS NOT NULL`

## GROUP BY 구

테이블에서 단순하게 데이터를 선택과 함께 합계 또는 평균 등의 집계 연산을 수행

```sql
SELECT gender, COUNT(*)
FROM Address
GROUP BY gender;
```

## HAVING 구

GROUP BY 구문을 통해 집합 테이블을 구하고 **그 결과에서 또!다시 조건**을 걸어 선택하는 기능

- WHERE vs HAVING
  - WHERE : '레코드'에 조건을 지정
  - HAVING : '집합'에 조건을 지정

```sql
SELECT address, COUNT(*)
FROM Address
GROUP BY address
HAVING COUNT(*) = 1;
```

## ORDER BY 구

- 쿼리에 대한 결과 순서를 지정
- `ORDER BY [필드명] (ASC | DESC) [필드명2] (ASC | DESC) ...`

## View 와 Sub Query

1. View

   - SELECT 구문을 데이터베이스 안에 저장 (테이블과 달리 내부에 데이터를 보유하지 않아 가상 테이블이라고 함)
   - View 생성

     ```sql
     CREATE VIEW [View Name] ([field name 1], [field name 2]) AS [SELECT 문]

     --- 예시 ---

     CREATE VIEW CountAddress (v_address, cnt)
     AS
     SELECT address, COUNT(*)
     FROM Address
     GROUP BY address;

     ```

   - View 사용

     ```sql
     SELECT v_address, cnt FROM CountAddress;
     ```

2. Sub Query

   ```sql
   SELECT v_address, cnt
   FROM (
   	SELECT address AS v_address, COUNT(*) AS cnt
   	FROM Address
   	GROUP BY address;
   	);
   ```

   - FROM 구에 직접 지정하는 SELECT 구문을 **서브 쿼리**라고 부름.
   - SQL은 서브쿼리부터 순서대로 실행
   - IN과 서브쿼리를 함께 사용하는 구문은 데이터가 변겨오디어도 따로 수정할 필요가 없다는 점에서 편리

## SQL과 조건 분기

1. CASE 식의 구문

   - 연산 후 결과 값으로 **특정한 값(상수)**를 리턴

   ```sql
   SELECT name, address
   	CASE
   			WHEN address='서울시' THEN '경기'
   			WHEN address='대구시' THEN '영남'
   			ELSE NULL
   	END
   	AS district
   FROM Address;
   ```

   - CASE는 `'식'` 이기 때문에 SELECT, WHERE, GROUP BY, HAVING, ORDER BY 구와 같은 곳 어디에나 적용 가능

## SQL의 집합 연산

1. UNION으로 합집합

   ```sql
   SELECT * FROM Address
   UNION
   SELECT * FROM Address2;

   --- 중복을 제외하고 싶지 않다면 ----

   SELECT * FROM Address
   UNION ALL
   SELECT * FROM Address2;
   ```

2. INTERSECT로 교집합

   ```sql
   SELECT * FROM Address

   INTERSECT

   SELECT * FROM Address2;
   ```

3. EXCEPT로 차집합

   ```sql
   SELECT * FROM Address

   EXCEPT

   SELECT * FROM Address2;
   ```

   - 주의사항 : 차집합은 교환 법칙이 성립하지 않습니다.

## 윈도우 함수

성능과 큰 관계가 있음 TODO: (8장에서 다룰 예정)

- GROUP BY 기능에서 집약 기능이 없다고 생각하면됨

  - ~~집약 기능 (COUNT, SUM, ...)~~
  - 자르기 (그룹별로 잘라서 하나만)

  ```sql
  SELECT address, COUNT(*) OVER(PARTITION BY address) FROM Address;
  ```

- 집약 함수 뒤에 `OVER` 구를 작성하고, 내부에 자를 키를 지정하는 `PARTION BY` 또는 `ORDER BY` 를 입력
- 윈도우 함수 전용 함수

  - RANK (+ DENSE_RANK)

    - 지정된 키로 레코드에 순위를 붙이는 함수

    ```sql
    SELECT name, age, RANK() OVER (ORDER BY age DESC) as RANK
    FROM Address;

    --- 순위 조건이 같다면 같은 순위를 표시함 (... 3,3,5...)
    --- 만약 건너뛰는 작업을 수행하고 싶지 않다면 DENSE_RANK 함수를 사용 (... 3,3,4, ...)
    ```

  - ROW_NUMBER
    - 동일한 순위에 대해서 고유의 순위를 부여하는 함수

## 트랜잭션과 갱신

SQL은 처음부터 데이터 검색을 중심으로 수행하기 위한 언어이며, 데이터를 갱신하는 것은 부가적인 기능.

- SQL의 갱신 작업 분류
  1. 삽입
  2. 제거
  3. 갱신

1. INSERT 데이터 삽입

   - RDB에서 데이터를 등록하는 단위는 레코드

   ```sql
   INSERT INTO [테이블이름] ([field1], [field2], [field3], ...)
   								VALUES ([value1], [value2], [value3], ...)
   ```

2. UPDATE 데이터 갱신

   ```sql
   UPDATE [테이블 이름] SET [field] = [value1], [field2] = [value2]
   WHERE [조건]; -- WHERE 은 옵션
   ```

3. 데이터 제거

   1. DELETE

      - 하나의 레코드 단위가 아니라 한 번에 여러 개의 레코드 단위로 처리하게 됨.

      ```sql
      DELETE FROM [테이블 이름]
      WHERE [조건]; -- WHERE 은 옵션
      ```

   2. TRUNCATED
      - 테이블 데이터를 저장하는 데 사용되는 페이지의 할당을 취소하여 테이블의 모든 행을 제거합니다.
      - delete보다 빠르지만 조건문을 사용하여 제거가 불가능하며 Transaction 구문에서 사용불가능합니당 ㅠ

---

# 🚨 SQL의 조건 분기 (구문→식)

case 식 이외에도 UNION 구문 등을 통해 조건 분기에 사용가능하지만, 이는 성능저하와 간결하지 않은 SQL 구문을 만들어 냅니다. 이 장에선 case를 통해 조건 분기를 하는 방법을 배웁니다.

## UNION을 사용한 쓸데없이 긴 표현

- UNION을 사용한 구문은 내부적으로 여러 개의 SELECT 구문을 실행하는 실행 계획으로 해석되어 테이블에 접근하는 횟수가 많아져 I/O 비용이 크게 늘어납니다.

```sql
SELECT item_name, year, price_tax_ex AS price
FROM Items
WHERE year <= 2001
UNION ALL
SELECT item_name, year, price_tax_ex AS price
FROM Items
WHERE year >= 2002;
```

- 두 개의 쿼리를 두 번이나 실행하고 있어서 성능이 떨어지며, SQL이 쓸데없이 길다!
- UNION은 편리한 도구로 쉽게 사용할 수 있지만 SQL 성능을 나쁘게 만듭니다.

## WHERE 구에서 조건 분기하는 사람은 뉴비

## 대신 SELECT 구문에서 CASE 식을 사용하여 조건 분기하세용

```sql
SELECT item_name, year,
	CASE
		WHEN year <= 2001 THEN price_tax_ex
		WHEN year >= 2002 THEN price_tax_in
	END AS price
FROM Items;
```

- 같은 결과를 출력하지만, 성능이 좋습니다!
  - **`실행 계획`**을 살펴보면 Items 테이블에 대한 접근이 1회로 줄어 듬. 또한 가독성도 좋아짐.
- 절차 지향형 언어에선 IF 조건문 == SQL에선 CASE

## 집계 대상으로 조건 분기

- 표측/표두 (column, row) 레이아웃 이동 문제
  - 원래 SQL은 이러한 결과 포멧팅을 목적으로 만들어져 있지 않지만 실무에서는 자주 사용 (데이터 집계)

```sql
SELECT perfecture,
				SUM(
					CASE
						WHEN sex = '1' THEN pop
						ELSE 0
					END
				) AS pop_men,
				SUM(
					CASE
						WHEN sex = '2' THEN pop
						ELSE 0
					END
				) AS pop_wom
FROM Population
GROUP BY prefecture;
```

- 기존 Union 으로 표현했던 표현보다 간결하며 실행 계획에서의 성능도 향상됨.

## 집약 결과로 조건 분기

UNION과 함께 GORUP BY 후 집약 결과에 조건 분기를 하는 HAVING 구문을 사용하는 사람도 뉴비임니다

```sql
SELECT emp_name,
	CASE
		WHEN COUNT(*) = 1 THEN MAX(team)
		WHEN COUNT(*) = 2 THEN '2개를 겸무'
		WHEN COUNT(*) >= 3 THEN '3개 이상을 겸무'
	END AS team
FROM Employees
GROUP BY emp_name;
```

## 그래도 UNION이 필요한 경우

### UNION을 사용할 수 밖에 없는 경우

- 예시 : 머지 대상이 되는 SELECT 구문들에서 사용하는 테이블이 다른 경우
- 물론 FROM 구에서 테이블을 결합하면 CASE 식을 사용해 원하는 결과를 얻을 수 있지만 필요 없는 결합이 발생해서 성능적으로 약영향이 발생. → 실행 계획을 통해 어떤 것이 더 좋은지 명확하게 확인해줘야 합니다.

### UNION을 사용하는 것이 성능이 더 좋은 경우

인덱스와 관련된 경우

UNION을 사용했을 때 좋은 인덱스를 사용하지만 이외의 경우에는 테이블 풀 스캔이 발생한다면 UNION을 사용하는 방법이 성능적으로 더 좋을 수 있습니다. (??)

[ThreeElements](https://www.notion.so/02ea9ee9d3314aaea535bcf74b2d3ec9)

`2013-11-01` 를 값으로 갖고 있고 대칭되는 플래그 필드의 값이 `'T'`인 레코드를 선택한다고 할 때,

```sql
SELECT key, name, date_1, flg_1, date_2, flg_2, date_3, flg_3
FROM ThreeElements
WHERE date_1 = '2013-11-01' AND flg_1='T'

UNION

SELECT key, name, date_1, flg_1, date_2, flg_2, date_3, flg_3
FROM ThreeElements
WHERE date_2 = '2013-11-01' AND flg_2='T'

UNION

SELECT key, name, date_1, flg_1, date_2, flg_2, date_3, flg_3
FROM ThreeElements
WHERE date_3 = '2013-11-01' AND flg_3='T'
```

여기서 포인트는 **`인덱스`!** 쿼리를 최적의 수행하려면 다음과 같은 인덱스가 필요.

```sql
CREATE INDEX IDX_1 ON ThreeElements (date_1, flg_1);
CREATE INDEX IDX_2 ON ThreeElements (date_2, flg_2);
CREATE INDEX IDX_3 ON ThreeElements (date_3, flg_3);
```

- 이러한 인덱스가 `WHERE date_* = '2013-11-01' AND flg_*='T'` 라는 필드 조합을 사용할 때 빠르게 쿼리를 만들어 줍니다.
- 테이블의 레코드 수가 많고 각각의 WHERE 구의 검색 조건에서 레코드 수를 많이 압축할수록, 테이블의 풀스캔보다도 훨씬 빠른 접근 속도를 기대할 수 있습니다.

### OR, IN, WHERE + CASE 구문은?

- OR, IN, CASE 구문은 `WHERE date_* = '2013-11-01' AND flg_*='T'` 라는 필드 조합을 사용할 수 없어 인덱스가 사용되지 않고 그냥 **1회의 테이블 풀 스캔이 진행이 되어 3회의 인덱스 스캔** 보다 더 오래걸립니다.
- 물론 테이블의 크기와 검색 조건에 따른 선택 비율(레코트 히트율)에 따라 답이 달라지겠지만 테이블이 크고, WHERE 조건으로 선택되는 레코드의 수가 충분히 작다면 UNION이 더 빠름.

## 그래서 하고싶은말은?

대부분 상황에서 UNION보다는 CASE 식을 사용하는 것이 성능적으로나 가독성에 좋은 결과가 있다.
