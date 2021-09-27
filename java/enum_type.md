# Enum Type name() & toString()

![](/src/images/enum-thumbnail.png)

- `name()` : enum의 대표값을 나타내며 method는 final이기 때문에 오버라이드 할 수 없다.
- `toString()` : 메서드는 열려있으며 오버라이드 할 수 있다. default로 `name()`을 호출한다.

그리고 voucherType을 그대로 출력했을 경우, `toString()` 이 호출 되는 것을 확인 할 수 있었습니다.

```
// voucherType 수정본
public enum VoucherType {
    PERCENTAGE("Percent"),
    FIXED("Fixed");

    private final String strVoucherType;

    // ...

    @Override
    public String toString() {
        return strVoucherType;
    }
}


// test code
class CreateCommandServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateCommandServiceTest.class);

    @Test
    @DisplayName("enum의 toString 와 name는 같지 않아야 한다.")
    void testEnumString() {
        // given
        VoucherType voucherType = VoucherType.FIXED;

        // when
        String nameValue = voucherType.name();
        String toStringValue = voucherType.toString();

        CreateCommandServiceTest.logger.error("name() : {}, toSting() : {}", nameValue, VoucherType.FIXED);

        // then
        assertThat(nameValue).isNotEqualTo(toStringValue);
    }
}
```
