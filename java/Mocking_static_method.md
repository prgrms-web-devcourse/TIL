# Mocking static method

# 배경 & 문제점

1. `VoucherService` 테스트 코드를 작성하는데 Mockito 라이브러리를 이용해서 행위에 대한 테스트를 구현한다.

    ```java
    class VoucherServiceTest {
        @Test
        @DisplayName("voucher가 생성되어야 한다.")
        void testCreateVoucher() {
    				// ...
    		}

    }
    ```

2. `Voucher` Class를 만드는 `VoucherFactory` 클래스의 method인 `createVoucher()` 가 정해진 인스턴스(`fixedAmountVoucher`)를 반환하도록 mocking하려고 한다.

    ```java
    // VocherFactory.class
    public class VoucherFactory {
        private static final Logger logger = LoggerFactory.getLogger(VoucherFactory.class);
        private static final VoucherFactory uniqueInstance = new VoucherFactory();
    
        private VoucherFactory() {
        }
    
        public static VoucherFactory getInstance() {
            return uniqueInstance;
        }
    
        public Voucher createVoucher(VoucherType type, UUID voucherId, long policyValue) {
            switch (type) {
                case PERCENTAGE:
                    return new PercentDiscountVoucher(voucherId, policyValue);
                case FIXED:
                    return new FixedAmountVoucher(voucherId, policyValue);
                default:
                    logger.error("Can't find voucher type");
                    throw new IllegalArgumentException();
            }
        }
    }
    
    ```

    ```java
    // in test code
    Voucher fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
    var voucherFactoryMock = mock(VoucherFactory.class);
    ```

3. 에러 발생!

    ```bash
    org.mockito.exceptions.base.MockitoException: 
    The used MockMaker SubclassByteBuddyMockMaker does not support the creation of static mocks
    
    Mockito's inline mock maker supports static mocks based on the Instrumentation API.
    You can simply enable this mock mode, by placing the 'mockito-inline' artifact where you are currently using 'mockito-core'.
    Note that Mockito's inline mock maker is not supported on Android.
    
    	at org.prgrms.kdt.voucher.service.VoucherServiceTest.testCreateVoucher
    ```

# 원인 분석

1.  `VoucherFactory` Class는 인스턴스를 찍어내는 Factory Pattern이기 때문에 Singleton 으로 구현하였다.
2. 그래서 `getInstance()`를 통해 유일한 자기 자신의 인스턴스를 반환하는데 이때 반환되는 값 또한 Mocking 되어야 한다.
3. 그리고 에러 로그를 살피던 중, static mock을 만들기 위해 `mockito-inline` artifact가 필요하다고 한다.

# 해결

1. 일단 `mockito-inline` 을 의존성에 추가한다.

    ```xml
    // pom.xml
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-inline</artifactId>
        <version>3.9.0</version>
        <scope>test</scope>
    </dependency>
    ```

2. Static Method에 대해서도 mocking 하자.

    ```java
    Voucher fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
    
    // 1. static method를 위한 static mock 클래스를 만들어 주고
    var voucherFactoryMockStatic = mockStatic(VoucherFactory.class);
    // 2. static method가 아닌 method를 우린 사용하기 때문에 VoucherFactory에 대해 기본 mock 객체를 만들어 준다.
    var voucherFactoryMock = mock(VoucherFactory.class);
    
    // 3. static method mocking 해주기
    voucherFactoryMockStatic
    	.when(VoucherFactory::getInstance)
    	.thenReturn(voucherFactoryMock);
    // 4. 이제 우리가 사용해야할 createVoucher 메서드에 대해 모킹해주자.
    when(voucherFactoryMock.createVoucher(
        VoucherType.FIXED, fixedAmountVoucher.getVoucherId(), 100
    )).thenReturn(fixedAmountVoucher);
    ```

# 느낀점

- 구글링도 구글링이지만, 구글링 전에 에러 로그들을 꼼꼼히 읽어보는 습관을 기르자...
- 테스트코드는 점점하면서 느는것 같다.