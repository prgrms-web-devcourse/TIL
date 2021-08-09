# Real-World Software Development

<div style="text-align:center">
    <img src="../../src/images/실전자바소프트웨어개발.jpeg" alt="drawing" width="200" text-align/>
    <div>[ 실전 자바 소프트웨어 개발 - 라울-게이브리얼 우르마, 리처드 워버턴 ]</div> 
</div>
<hr>

## 목차

- Chapter1. 여행의 시작
- Chapter2. 입출금 내역 분석기

<hr>

## 1장

1장에서는 간단하게 책에서 사용하는 개념과 원칙을 소개한다. (자바, 소프트웨어 디자인과 아키텍처, SOLID 및 테스트)

## 2장

2장에서는 [입출금 내역을 자동으로 분석해 재정상태를 더 잘보여주는 소프트웨어](./BankTransactionAnalyzerSimple.java)를 개발하면서 좋은 소프트웨어 개발의 기반이 무엇인지 배운다.

### KISS 원칙

- KISS(Keep In Short and Simple) : 소프트웨어 디자인을 간단하고 단순하게 하는 것.
  - 일단 응용프로그램 코드를 한 개의 클래스로 구현함.
- 문제점 (예외 사항)

  - _파일이 비어 있다면?_
  - _데이터에 문제가 있어서 금액을 파싱하지 못 한다면?_
  - _행의 데이터가 완벽하지 않다면?_

  > 언제나 이런 질문을 하는 습관을 가지는 것이 좋음

### 단일 책임 원칙 (SRP)
