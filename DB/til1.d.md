# TIL : 스키마

- [x]  데이터 베이스 강의 듣기
- [x]  알게 된 내용
    - 스타 스키마
    - 스노우 레이크 스키마 (눈송이 스키마)
    - Denormalized Schema (역 정규화된 스키마)Denormalized Schema (역 정규화된 스키마)
- [x]  느낀 점

## 스타 스키마

- 데이터 웨어하우스 스키마 중 가장 단순한 종류의 스키마
- Join 스키마라고 불림
- 하나의 사실 테이블을 중심으로 다수의 차원 테이블들로 구성
- FACT 테이블 : 보통 제 3정규형
- Dimension 테이블 : 보통 역 정규화된 제 2정규형

![TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled.png](TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled.png)

스타 스키마 / 이미지 참고 : [https://jmkim.tistory.com/84](https://jmkim.tistory.com/84) 

- Production DB 관계형 DB에서는 보통 스타 스키마를 사용해 데이터를 저장
- 장점
    - 복잡도가 낮고 이해하기 쉬움
    - 쿼리 작성이 용이하고 조인 수가 적어 SQL 쿼리 성능이 좋다
- 단점
    - 비정규화에 따른 데이터 중복 발생하여 더 많은 디스크 공간을 차지

## 눈송이 스키마

- 차원 테이블(Dimension Table)이 하나 이상의 차원에 연결된 스타 스키마의 확장
- 장점
    - 중복을 줄이기 위한 정규형으로 유지 → 저장공간의 절약
- 단점
    - 더 많은 조인을 필요로 하여 검색의 효과 감소 → 시스템의 악영향

![TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%201.png](TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%201.png)

눈송이 스키마 / 이미지 참고 : [https://jmkim.tistory.com/84](https://jmkim.tistory.com/84)

![TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%202.png](TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%202.png)

[https://ko.wikipedia.org/wiki/눈송이_스키마#/media/파일:Snowflake-schema-example.png](https://ko.wikipedia.org/wiki/%EB%88%88%EC%86%A1%EC%9D%B4_%EC%8A%A4%ED%82%A4%EB%A7%88#/media/%ED%8C%8C%EC%9D%BC:Snowflake-schema-example.png)

## Denormalized Schema (역 정규화된 스키마)

- NoSQL이나 데이터 웨어하우스에서 사용하는 방식
- 단위 테이블로 나눠 저장하지 않으므로 별도의 조인이 필요 없는 형태
- 장점 - 별도의 조인이 필요 없기에 빠른 계산이 가능
- 단점 : 데이터 스토리지 낭비 발생

![TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%203.png](TIL%20%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B5%E1%84%86%E1%85%A1%202cfa5a20333046938da8ef8f8f19e6a4/Untitled%203.png)

테이블 하나로 묶는 것 / [https://doc-archives.microstrategy.com/producthelp/10.8/ProjectDesignGuide/WebHelp/Lang_1033/Content/ProjectDesign/Moderately_normalized_schema__Balanced_storage_spa.htm](https://doc-archives.microstrategy.com/producthelp/10.8/ProjectDesignGuide/WebHelp/Lang_1033/Content/ProjectDesign/Moderately_normalized_schema__Balanced_storage_spa.htm)

## 느낀 점

- 평소에 몰랐던 스키마의 종류에 대해 알게 됨
- 처음 DB를 배웠을때 "힘들게 join을 해야되기 때문에 테이블을 귀찮게 왜 나누었는지 몰랐는데" 이번 기회에 왜 테이블을 나누었는지 알게 되었고 필요에 따라 여러 종류의 스키마가 있다는 사실을 알게 됨.
- 관계형 DB도 사용에 따라 각 종류의 스키마가 있고 가장 많이 사용하는 스키마는 "스타 스키마"

### Ref

[https://ko.wikipedia.org/wiki/스타_스키마](https://ko.wikipedia.org/wiki/%EC%8A%A4%ED%83%80_%EC%8A%A4%ED%82%A4%EB%A7%88)

[http://publib.boulder.ibm.com/tividd/td/TEDW/GC32-0745-00/ko_KO/HTML/aegmst108.htm](http://publib.boulder.ibm.com/tividd/td/TEDW/GC32-0745-00/ko_KO/HTML/aegmst108.htm)