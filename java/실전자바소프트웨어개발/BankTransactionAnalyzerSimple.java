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

  - 결합도
    1. BankStatementAnalyzerSimple은 BankStatementCSVParser에 의존한다.
        -> 인터페이스를 사용하여 여러 컴포넌트의 결합도를 제거하자
 */

// 0. 입출금 내역 도메인 클래스
public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amount, final String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                date.equals(that.date) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date ,amount, description);
    }
}

// 1. 입력 읽기,  4. 결과 요약리포트
// 결합도 1. 인터페이스를 사용하여 BankTransactionParser를 인수로 받는 analyze() 메서드를 만들어 특정 구현에 종속되지 않도록 클래스를 개선한다.
//  => 클래스가 더 이상 특정 구현에 종속되지 않도록 개선되었으므로 요구 사항이 바뀌어도 쉽게 대응할 수 있다.
public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";
    
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    public static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transaction is " + bankStatementProcessor.calculateTotalAmount());

        System.out.println("Transaction in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("Transaction in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("the total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));;

    }
}

// 2. 파싱 로직을 추출해 한 클래스로 만듦
// 결합도 1. BankStatementParser 인터페이스를 구현한다.
public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}

// 3. 결과 처리,
public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month)
                total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction: bankTransactions) {
            if (bankTransaction.getDescription().equals(category))
                total += bankTransaction.getAmount();
        }
        return total;
    }
}

// 결합도 1. BankStatementParser interface를 만든다.
public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}

// 결합도 1. 메인 응용프로그램에서 지금까지 구현한 코드를 사요하자.
public class Main {
    public static void main(String[] args) throws IOException {

        final BankTransactionAnalyzerSimple bankTransactionAnalyzerSimple
                = new BankTransactionAnalyzerSimple();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        bankTransactionAnalyzerSimple.analyze(args[0], bankStatementParser);
        
    }
}
