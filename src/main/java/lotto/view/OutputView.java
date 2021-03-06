package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.enums.Rank;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printLottoTicketsMessage(List<LottoTicket> manualTickets, List<LottoTicket> autoTickets) {
        System.out.println("\n수동으로 " + manualTickets.size() + "장, "
                + "자동으로 " + autoTickets.size() + "개를 구매했습니다.");
    }

    public void printLottoTickets(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(getLottoTicketString(lottoTicket));
        }
        System.out.println();
    }

    public StringBuilder getLottoTicketString(LottoTicket lottoTicket) {
        List<LottoNumber> lottoNumbers = lottoTicket.getLottoTicketNumbers();

        StringBuilder sb = new StringBuilder("[");
        sb.append(lottoNumbers.stream()
                        .map(lottoNumber -> lottoNumber.toString())
                        .collect(Collectors.joining(", ")))
                .append("]");

        return sb;
    }

    public void printStatistics(double profitRate) {
        System.out.printf("총 수익률은 %.2f 입니다.\n", profitRate);
    }

    public void printLottoResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        Map<Rank, Integer> rankResult = lottoResult.getLottoResult();
        rankResult.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Rank::getPrize)))
                .forEach(rankResultEntry -> result(rankResultEntry.getKey(), rankResultEntry.getValue()));
    }

    public void result(Rank rank, int count) {
        if (!rank.isSecondPlace(rank)) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getMatched(), rank.getPrize(), count);
        }
        if (rank.isSecondPlace(rank)) {
            System.out.printf("%d개 일치, 보너스볼 일치 (%d원)- %d개\n", rank.getMatched(), rank.getPrize(), count);
        }
    }
}