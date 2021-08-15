# [Day1]

이벤트 날짜: 2021년 8월 2일 → 2021년 8월 6일
작성일시: 2021년 8월 4일 오전 3:02
주차: Week1

이선협 강사님 추천 링크

- [Cobolt Design](https://design.caple.ai/?path=/story/introduction-quick-view--default)

브라우저의 동작 원리

브라우저가 어떻게 동작하는지 알아보자

1. 서버 → 브라우저 ↔︎ 서버
2. 렌더링 → 돔(통신을 통해서 받은 HTML을  브라우저가 받아 생성 : 트리구조로 구성)이라는 객체를 화면에 그리는 것
3. 스크립트 실행 → 자바스크립트를 실행

$Reference$

[🌐  https://d2.naver.com/helloworld/59361](https://d2.naver.com/helloworld/59361)

[🌐  https://ko.wikipedia.org/wiki/자바스크립트_엔진](https://ko.wikipedia.org/wiki/자바스크립트_엔진)

[🌐  https://poiemaweb.com/js-browser](https://poiemaweb.com/js-browser)

변수

직접 메모리에 할당한 값

var : 호이스팅이라는 자바스크립트의 특이한 동작 때문에 권장 x(오늘의 숙제)

let ES6 이후 사용 가능! 

```jsx
let       age        =          27
키워드     변수명                   값
			= 메모리 상 주소      = 주소에 해당하는 값
```

상수

변하지 않는 수

`const`

자료형

```jsx
// Number 타입
let integer = 161;
let float = 161.8;
let nan = parseInt('abc'); // NaN
let inf = 1 / 0; //Infinity

// String 타입
let string1 = "'String'";
let string2 = '"String"';
let string3 = `-${string2}-`; // `문자+변수` 백틱으로 감쌀때만 가능
let string4 = 'I\'m String';  // ' \' ' 이스케이프 문자와 함께 사용하면 작은따옴표안에 작은따옴표 가능.
															// 이스케이프는 자기 자신을 이스케이프 할 수 있음

// Boolean 타입

// Object 타입
let object = {
	name: "Jihyeon Lee",
	height: 161.8,
};
object['height'] // 161.8
key : value // key는 무조건 문자열

// Array 타입
let arry = [0, 1, 2, 'a', true]
array[0] // 0
array[3] // 'a'
array[4] // true

// Function 타입
let func = function () {
	return 0;
};

// Undefined : 값이 정의되지 않음

// Null : null이라는 값을 넣음. 
//		  	해당 변수가 비어있음을 나타냄
```

메모리

자바스크립트에서는 메모리를 어떻게 다루는지

1. 할당 : 변수 선언
2. 사용 : 할당된 메모리에 값을 넣어 사용
3. 해제 :  Garbage Collector

메모리 심화

```jsx
let variable = 161
let variable2 = variable // 값이 저장된 메모리의 주소를 참조함
variable = variable +1  // 새로운 메모리 주소를 할당받고 값을 넣음
											  // why? 자바스크립트에서 원시타입은 변경 불가

```

Heap : 참조타입 e.g. 배열 (동적으로 크기 변경 가능)

Call Stack : 원시타입

Mark and Sweep Algorithm

: 닿을 수 없는 주소를 더 이상 필요없는 주소로 정의하고 지우는 알고리즘

표현식과 연산자

표현식
: 어떠한 결과 값으로 평가되는 식이다.
  숫자, 문자열, 논리값 같은 원시 값을 포함하여 변수, 상수, 함수 호출 등으로 조합할 수 있다.

연산자의 종류

할당 연산자
  :할당 연산자는 오른쪽 표션식을 왼쪽 피연산자 갑에 할당하는 연산자
  등호를 사용하여 다른 연산자와 같이 사용하여 복합 할당 연산자로 이용할 수 있다.

비교 연산자
  :좌측 피연산자와 우측 피연산자를 비교하는 연산자. true 혹은 false를 반환한다.

산술 연산자
  :덧셈, 뺄셈, 곱셈, 나눗셈을 하는 연산자 Number를 반환한다.

비트 연산자(코테때 가끔 사용)
  :비트를 직접 조작하는 연산자

논리 연산자
  :Boolean을 통해 참과 거짓을 검증하는 연산자

삼항 연산자(편의를 위해 조건문 대신 사용함)
  :조건에 따라 값을 선택하는 연산자 '조건 ? 참 : 거짓' 형태를 가진다.

관계 연산자
  :객체에 속성이 있는지 확인하기 위한 연산자

typeof
  :피연산자의 타입을 반환하는 연산자 문자열로 반환된다.

흐름제어

Control Flow (함수형 프로그램 방식)

IF / Switch / For / While / do-While

배열과 객체

```jsx
const arr1 = new Array(); // []
const arr2 = [];          // []
const arr3 = [1, 2, 3, 4, 5]; // [ 1, 2, 3, 4, 5 ]
const arr4 = new Array(5); // [ , , , , ]
const arr5 = new Array(5).fill(5); // [ 5, 5, 5, 5, 5 ]
const arr6 = Array.from(Array(5), function (v, k) {
	return k + 1;
}) // [ 1, 2, 3, 4, 5 ]

// join 
arr3.join(","); // 1, 2, 3, 4, 5

// reverse 단, 한 번 사용하면 원본 배열도 뒤집힘
arr3.reverse(); //[ 5, 4, 3, 2, 1 ]

// concat
arr3.concat(arr5); // [ 1, 2, 3, 4, 5, 5, 5, 5, 5, 5 ]

// push 배열 끝에 추가

// pop 배열 끝을 삭제

// shift 배열 앞에 삭제

// unshift 배열 앞에 추가

// slice .slice(시작 index, 끝 index 앞) 단, 원본 배열은 변화 없음

// splice .splice(시작 index, 삭제할 개수) 삭제되고 나머지 요소만 남음

// for of 배열의 순회
for (const item of arr3){
	console.log(item);
}

```

객체

```jsx
const obj = { name : "알리스", height : "161.8" };

obj["favFood"] = "Sogobchang";
obj.weight = "x8";
console.log(obj);
// { name : "알리스", height : "161.8", favFood : "sogobchan", weight : "x8" }

delete obj.weight;
console.log(obj);
// { name : "알리스", height : "161.8", favFood : "sogobchang" }

console.log("height" in obj); // true
console.log("weight" in obj); // false

Object.keys(obj); // [ "name", "height", "favFood" ]
Object.values(obj); // [ "알리스", "161.8", "sogobchang" ]

// 객체 순회 5for in
for (const key in obj){
	console.log(key, obj[key]);
} // name 알리스 height 161.8 favFood sogobchang
```

스코프와 클로저

스코프

: 유효범위. 변수가 어느 범위까지 참조되는 지를 뜻함

```jsx
const a = 5; // 전역 스코프
{
	const b = 3; // 지역 스코프
	console.log(a, b); // 5  3
}
console.log(a, b); // Error!
---------------------------------

var c = 5;
{
	// 호이스팅 되어 변수 선언이 상단으로 올라가버린다.
	var c = 10;
	console.log(c); // 10
}
console.log(c); // 10

```

var 는 함수수준의 스코프 let, cons 는 블록수준의 스코프

클로저

: 함수가 선언된 환경의 스코프를 기억하여 함수가 스코프 밖에서 실행될 때에도 기억한 스코프에 접근          할 수 있게 만드는 문법

```jsx
function makeGreeting(name) {
	const greeting = "Hello, ";
	
	return function () {
		console.log(greeting + name);
	};
}

const world = makeGreeting("World!");
const Jihyeon = makeGreeting("Jihyeon");

world();
Jihyeon();
```

은닉화

: 클로저를 이용하여 내부 변수와 함수를 숨길 수 있다.

```jsx
function Counter(){
	let privateCounter = 0;
	function changeBy(val) {
		privateCounter += val;
	}
	return {
		increment: function () {
			changeBy(1);
		},
		decrement: function (){
			changeBy(-1);
		},
		value: function () {
			return privateCounter;
		},
	};
}
const counter = counter();

console.log(counter.value()); // 0
counter.increment();
counter.increment();
console.log(counter.value()); // 2
counter.decrement();
console.log(counter.value()); // 1

```

```jsx
function counting(){
	let i = 0;
	for (i = 0; i < 5; i += 1) {
		setTimeout(function (){
			console.log(i);
		}, i * 100);
	}
}

counting(); // 5 5 5 5 5

// 해결방법 1
// IIFE를 이용
function counting(){
	let i = 0;
	for (i = 0; i < 5; i += 1) {
		(function (number) {
			setTimeout(function (){
				console.log(number);
		}, number * 100);
	})(i);
}

counting();

// 해결방법 2
// let은 블록 수준 스코프기 때문에 매 루프마다 클로저가 생성된다.
function counting(){
	for (let i = 0; i < 5; i += 1) {
		setTimeout(function (){
			console.log(i);
		}, i * 100);
	}
}

counting();
```

네트워크 기초

브라우저에 url을 입력했을때 일어나는일

1. URL을 해석 (스키마, 계정정보, 등등)
2. DNS을 조회 (도메인과 IP주소를 서로 변환)
3. 해당 IP가 존재하는 서버로 이동
4. ARP를 이용하여 MAC 주소 변환
5. TCP 통신을 통해 Socket을 열어야 함 (3way handshake)
6. 서버는 응답을 반환
7. 브라우저는 렌더링

\+ 알아야 할 것들

OSI7계층

Routing Table

Subnet mask

TCP Socket Stream

선택과제

- http가 이미 있음에도 불구하고 https가 왜 탄생했는지, 왜 필요한지 조사
- https가 생김으로 인해 기존 과정에서 무엇이 추가되었는지 조사

컴퓨터 시간 원리

기간은 어떻게 결정될까?

- 물리량
- 위치
- 천문 현상
- 문화 (양음력, 이슬람력, 에티오피아력)
- 역사 (그레고리력)
- 사회 (Summertime)

협정 세계시(UTC)

컴퓨터가 시간을 표현하는 방법

시스템 클럭의 원리

Unix Time(1970년 1월 1일 기준)

현재 시간을 어떻게 알아낼까?

NTP 서버 (트리구조)

시간대를 어떻게 고려할까?

시간을 어떤 기준으로 사용해야 할까?

서비스에서 사용되는 시간을 용도에 맞춰 사용해야 함

- 순수한 시간

    생일 , 기업 설립일, 기념일, 국경일

- UTC

    로깅, 감사, 시계열 데이터(주식)

- Time Zone이 적용된 시간

    결제 시각, 푸시 알림 시간, UI 시각 표시, 캘린더

JavaScript에서 사용법

- 간단하게 사용하면 Date 객체를 사용
- moment.js는 Deprecated 됨
    - 대체 라이브러리로 date-fns, luxon을 사용할 수 있다.

$cf$ [강사님 블로그](https://kciter.so/posts/deep-dive-into-datetime)

암호화 기초

암호화

: 평문을 암호문으로 변환하는것

단방향 암호화

: 해시 알고리즘을 이용 (MD5, SHA 알고리즘)

사용자 비밀번호를 저장할 때 사용

MD5와 SHA-0, SHA-1은 해시 충돌이 발생할 수 있어 권장 X

[RAON CTF - WEB Essential](https://raonctf.com/essential/study/web/cryptography)

단방향 암호화에서 고려할 점

Salt

Key stretching

양방향 암호화

평문을 복호화 할 수 있는 형태로 암호화하는 방법

대칭키와 비대칭키 알고리즘

대칭키 암호 알고리즘

:대표적으로 AES

비대칭키 암호 알고리즘

:대표적으로 RSA , 공개키와 개인키 두 가지 키가 존재한다.

JavaScript에서 암호화 하는 방법

- [crypto-js](https://github.com/brix/crypto-js)를 사용
- crypto-js에 [bcrypt](https://github.com/kelektiv/node.bcrypt.js)는 구현되어있지 않아 다른 라이브러리를 알아봐야한다.

---

TIL이 중요하다는 얘기는 많이 들어봤지만 길게 글을 쓴다는 것 부터가 좀 부담스럽다. 일단은 강의를 들으면서 텍스트문서로 필기만 했지만 주말이나 스터디를 통해 클로저와 OS7계층 등을 공부해야겠다.