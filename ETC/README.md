# **Markdown**

    마크다운(markdown)은 일반 텍스트 기반의 경량 마크업 언어다. 
 일반 텍스트로 서식이 있는 문서를 작성하는 데 사용되며, 일반 마크업 언어에 비해 문법이 쉽고 간단한 것이 특징이다. HTML 등 서식 문서로 쉽게 변환되기 때문에 README 파일이나 온라인 게시물 등에 많이 사용된다.



<br>
<p></p>

# 마크다운 문법

## **1. 제목**
* '#'를 사용하는 방법

```
# 제목은
### 1개부터
###### 6개까지 가능하다.
```

* '---' 또는 '===' 사용
```
제목
====
부제목
---
```

## **2. 목록**
* 순서가 있는 목록
```
1. 첫번째
2. 두번째
3. 세번째
```
* 순서가 없는 목록 (*, +, -)
```
* 1번글
    + 1-1번글
    + 1-2번글
* 2번글
```
* 1번글
    + 1-1번글
    + 1-2번글
* 2번글

<br>

## **3. 인용문구**
```
> blockQuote
> >blockQuote
```
> blockQuote
> >blockQuote

<br>

## **4. 코드삽입**
* "```" 사용 <br>
github 에서는 코드 시작점에 사용하는 언어를 선언하여 문법강조가 가능하다. (필수는 아님)
<pre>
```java
코드들..
```
</pre>

* "pre태그사용"
```
<pre>
<code>
코드들..
</code>
</pre>
```


## **5. 수평선**
```
***
-----
<hr/>
```
***
-----
<hr/>

<br>

## **6. 강조(*, _ 사용)**
```
*이태릭체*
**볼드체**
***이태릭+볼드체***
~~취소선~~
```
*이태릭체*
**볼드체**
***이태릭+볼드체***
~~취소선~~

<br>

## **7. 띄어쓰기**
띄어쓰기를 여러번 &nbsp;&nbsp;&nbsp;적용을하고 싶다면 
```
&nbsp;&nbsp;&nbsp;
```

<br>

## **8. 줄바꿈**
```
문장 마지막에서 세칸뛰어쓰기를 하면된다.   
혹은 <br> 
혹은 <p> </p>를 사용하면된다.

```
문장 마지막에서 세칸뛰어쓰기를 하면된다.   
혹은 <br> 
혹은 <p> </p>를 사용하면된다.

<br>

## **9. 링크**
* 참조 링크
```
Link1 : [Google][googlelink]

[googlelink]: https://google.com "Go google"

```
Link1 : [Google][googlelink]

[googlelink]: https://google.com "Go google"


* 인라인 링크(=외부링크)
```
Link1 : [Google](https://google.com, "google link")

Link2 : <a href="https://www.naver.com" target="_blank" title="네이버">naver</a>

```
Link1 : [Google](https://google.com, "google link")
Link2 : <a href="https://www.naver.com" target="_blank" title="네이버">naver</a>

* URL 링크, 이메일링크
```
URL 링크: <http://example.com/>
이메일링크: <address@example.com>
```
URL 링크: <http://example.com/>
이메일링크: <address@example.com>
* 다른 md파일로 이동

상대 경로를 이용해야한다.   

예1) 현재 위치인 ETC파일의 markdown.md파일에서 상위의 README.md로 이동하고 싶다면? 
```
[README.md로 이동](../README.md)

```
[README.md로 이동](../README.md)   

예2) 현재 위치인 ETC파일의 markdown.md파일에서 spring의 controller/ HttpServletRequest, HttpServletResponse.md로 이동하고 싶다면? 
```
[controller로 이동](../Spring/Controller/HttpServletRequest, HttpServletResponse.md)

```
[controller로 이동](../Spring/Controller/HttpServletRequest.md)

* 같은 md파일 안에서의 이동
```
[같은 md파일 안에서 이동](#9.-링크)
주의할 점 : 공백은 -으로바꾼다.
이모지, 마침표(.), 콤마(,)생략 
```

[같은 md파일 안에서 이동](#9.-링크)

<br>

## **10. 이스케이프문자**
```
\### 출력
### 출력
```
\### 출력
### 출력
<br>

## **11. 이모티콘**
* 체크박스   
    + [] 빈 체크박스
    + [X] 체크박스
```
    * [] 빈 체크박스
    * [X] 체크박스
```
* 이모지  
많이 사용하는 건 : 💡✔️❌❓❔👀➕➖⚠️📝🐧👍👎
https://gist.github.com/rxaviers/7360908

<br>

## **13. 요약**
<details>
<summary>요약하기</summary>

클릭시에는 요약의     
설명이 뜹니다!
</details>


## **12. 이미지**
```
!를 이용하면된다.
![엑박시 보여질 이미지명](이미지주소)](링크주소)

![puppy-1903313_640](https://user-images.githubusercontent.com/31085727/39238230-b2ebb084-48b8-11e8-9e06-0a1743b253e7.jpg "강아지")

<img src="https://user-images.githubusercontent.com/31085727/39238230-b2ebb084-48b8-11e8-9e06-0a1743b253e7.jpg" width="100px" height="100px" title="px(픽셀) 크기 설정" alt="강아지"/>
```

![puppy-1903313_640](https://user-images.githubusercontent.com/31085727/39238230-b2ebb084-48b8-11e8-9e06-0a1743b253e7.jpg "강아지")
<img src="https://user-images.githubusercontent.com/31085727/39238230-b2ebb084-48b8-11e8-9e06-0a1743b253e7.jpg" width="100px" height="100px" title="px(픽셀) 크기 설정" alt="강아지"/>

이미지의 src를 얻어 내기 위해 약간의 트릭을 사용할 수 있다.   
바로 github 프로젝트의 Issue를 이용하는 것이다. 이 방법으로 이미지 뿐만아니라 동영상도 추가할 수 있다.
```
1. github 프로젝트의  Issue탭으로 들어간다.
2. New Issue버튼
3. 얻고자하는 이미지를 드래그 앤 드랍
4. "<img src=~~>" 형식의 문자열로 반환된다. 이를 사용하면된다.
```
<br>

## **13. 테이블**
```
<!-- Markdown -->
Title1|Title2
-|-
content1|content2
content3|content4
  
Title1|Title2|Title3
:-|:-:|-:
content1|content2|content3
<!-- Html -->
<figure>
    <table>
        <thead>
            <tr>
                <th>Title1</th>
                <th>Title2</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>content1</td>
                <td>content2</td>
            </tr>
            <tr>
                <td>content3</td>
                <td>content4</td>
            </tr>
        </tbody>
    </table>
</figure>
  
<figure>
    <table>
        <thead>
            <tr>
                <th style='text-align:left;' >Title1</th>
                <th style='text-align:center;' >Title2</th>
                <th style='text-align:right;' >Title3</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td style='text-align:left;' >content1</td>
                <td style='text-align:center;' >content2</td>
                <td style='text-align:right;' >content3</td>
            </tr>
        </tbody>
    </table>
</figure>
```
<!-- Markdown -->
Title1|Title2
-|-
content1|content2
content3|content4
  
Title1|Title2|Title3
:-|:-:|-:
content1|content2|content3
<!-- Html -->
<figure>
    <table>
        <thead>
            <tr>
                <th>Title1</th>
                <th>Title2</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>content1</td>
                <td>content2</td>
            </tr>
            <tr>
                <td>content3</td>
                <td>content4</td>
            </tr>
        </tbody>
    </table>
</figure>
  
<figure>
    <table>
        <thead>
            <tr>
                <th style='text-align:left;' >Title1</th>
                <th style='text-align:center;' >Title2</th>
                <th style='text-align:right;' >Title3</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td style='text-align:left;' >content1</td>
                <td style='text-align:center;' >content2</td>
                <td style='text-align:right;' >content3</td>
            </tr>
        </tbody>
    </table>
</figure>