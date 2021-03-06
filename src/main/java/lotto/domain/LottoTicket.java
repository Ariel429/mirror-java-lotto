package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (nonDuplicateNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개로 이뤄져 있습니다.");
        }
    }

    public List<LottoNumber> getLottoTicketNumbers() {
        return numbers;
    }

    public int countOfMatches(LottoTicket lastWeekWinningTicket) {
        Set<LottoNumber> lastWeekWinningNumbers = new HashSet<>(lastWeekWinningTicket.getLottoTicketNumbers());
        Set<LottoNumber> purchasedTicket = new HashSet<>(numbers);
        lastWeekWinningNumbers.removeAll(purchasedTicket);
        return LOTTO_NUMBERS_SIZE - lastWeekWinningNumbers.size();
    }

    public int countOfBonusMatch(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber) ? 1 : 0;
    }
}
