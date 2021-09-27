# Heap

우선 순위 큐를 하다가 `Heap` 구조를 사용한다는 것을 보았는데 Heap 자료구조란 무엇일까?

힙은 일종의 **BinaryTree** 이며 수의 집합에서 가장 작은 수나 가장 큰 수만을 자주 꺼내올 때 유용한 자료구조

가장 큰 / 작은 수 가져올때 시간 복잡도 : O(log N)

## 예: 최소 힙 구현

### 규칙이 있다.

- 루트는 가장 작은 값이여야 함.
- 자식은 자신보다 크기만 하면됨.
- 완전 이진 트리의 규칙을 그대로 적용
- **힙을 배열 형태로 구현**
  - 왼쪽 자식은 (자신의 인덱스 \* 2)
  - 오른쪽 자식은 (자신의 인덱스 \* 2 + 1)
  - 자신의 부모는 (자신의 인덱스 / 2)

### Heap 정의

```java
public class Heap {
	public static final int MAX_N = 10001;
	public int[] arr;
	public int size;

	public Heap() {
		this.arr = new int[MAX_N];
		this.size = 0;
	}

}
```

### Insert

- 일단 잎 노드부터 계속 비교하여 자리를 찾아가야 함.

```java
public class Heap {
	// ...
	void insert(int data) {
		int current = ++size;

		while ( (current != 1) && (data < arr[current / 2]) ) {
			arr[current] = arr[current / 2]; // 내려가시구~
			current /= 2;
		}
		arr[current] = data;
	}
}
```

### Delete

- delete는 root 위치를 꺼내는 작업임
- 먼저 가장 맨 아래(`arr[size]`)에 있는 데이터를 올립니다.
- 자식과 비교해서 바꾸면서 내려감

```java
public class Heap {
	// ...
	int delete() {
		if (size == 0) return -1;

		int ret = arr[1];
		arr[1] = arr[size--]; // 데이터 올림 그리고 사이즈 감소

		int parent = 1;
		int child;

		while(1) {
			child = parent * 2;
			if (child + 1 <= size && arr[child] > arr[child + 1])
				child++;

			if (child > size || arr[child] > arr[aprent] ) break;
			swap(parent, child);
			parent = child;
		}

		return ret;
	}

	void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
```

---

힙을 알았으니 우선순위큐를 사용하여 문제를 풀어봅시다.

## 프로그래머스: 디스크 컨트롤러

- 문제 링크
  - [https://programmers.co.kr/learn/courses/30/lessons/42627](https://programmers.co.kr/learn/courses/30/lessons/42627)
- 풀이
  - [https://github.com/dlfdyd96/algorithm/blob/5e275ab00923d3cbd939f430911c89c9e01e1d31/src/prgrms/weekly/w6/W6.java](https://github.com/dlfdyd96/algorithm/blob/5e275ab00923d3cbd939f430911c89c9e01e1d31/src/prgrms/weekly/w6/W6.java)
