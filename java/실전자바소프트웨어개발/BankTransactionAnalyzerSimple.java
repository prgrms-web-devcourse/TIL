/**
  - 요구사항
    - 은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가? 결과가 양수인가 음수인가?
    - 특정 달엔 몇 건의 입출금 내역이 발생했는가?
    - 지출이 가장 높은 상위 10건은 무엇인가?
    - 돈을 가장 많이 소비하는 항목은 무엇인가?
    
  - SRP 적용하기 위한 책임 분리
    1. 입력 읽기
    2. 주어진 형식의 입력 파싱
    3. 결과 처리
    4. 결과 요약리포트
 */
public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;

        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }

        System.out.println("The total for all transaction is " + total);
    }
}