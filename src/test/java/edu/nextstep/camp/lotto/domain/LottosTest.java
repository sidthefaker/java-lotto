package edu.nextstep.camp.lotto.domain;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottosTest {
    @Test
    public void create() {
        final Lotto testLotto = Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));

        assertThat(Lottos.of(List.of(testLotto)))
                .isEqualTo(Lottos.of(List.of(
                        Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)))
                )));
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullAndEmptySource
    public void createFailed(List<Lotto> lottos) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lottos.of(lottos))
                .withMessageContaining("invalid input");
    }

    @Test
    public void amount() {
        final Lottos testLottos = Lottos.of(List.of(
                Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)))
        ));
        assertThat(testLottos.amount()).isEqualTo(1);
    }

    @Test
    public void collect() {
        final List<Lotto> lottoList = List.of(Lotto.of(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))
        ));
        final Lottos testLottos = Lottos.of(lottoList);
        assertThat(testLottos.collect()).hasSameElementsAs(lottoList);
    }

    static Stream<Arguments> parseWinningResult() {
        return Stream.of(
                // 0 matched
                Arguments.of(
                        Lottos.of(List.of(Lotto.of(List.of(LottoNumber.of(10), LottoNumber.of(11), LottoNumber.of(12),
                                LottoNumber.of(13), LottoNumber.of(14), LottoNumber.of(15))))),
                        GameResult.of(0, 0, 0, 0)
                ),
                // 3 matched
                Arguments.of(
                        Lottos.of(List.of(Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                LottoNumber.of(13), LottoNumber.of(14), LottoNumber.of(15))))),
                        GameResult.of(0, 0, 0, 1)
                ),
                // 6 matched
                Arguments.of(
                        Lottos.of(List.of(Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))))),
                        GameResult.of(1, 0, 0, 0)
                ),
                // 3 matched with 2 lottos
                Arguments.of(
                        Lottos.of(List.of(
                                Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(13), LottoNumber.of(14), LottoNumber.of(15))),
                                Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(13), LottoNumber.of(14), LottoNumber.of(15)))
                        )),
                        GameResult.of(0, 0, 0, 2)
                ),
                // 6 matched with 2 lottos
                Arguments.of(
                        Lottos.of(List.of(
                                Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))),
                                Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)))
                        )),
                        GameResult.of(2, 0, 0, 0)
                )
        );
    }

    @ParameterizedTest(name = "check winning: {0} -> {1}")
    @MethodSource("parseWinningResult")
    public void winningResult(Lottos lottos, GameResult expected) {
        final Lotto winningNumber = Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
        assertThat(lottos.winningResult(winningNumber))
                .isEqualTo(expected);

    }
}
