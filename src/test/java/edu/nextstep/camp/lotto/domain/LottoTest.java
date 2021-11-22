package edu.nextstep.camp.lotto.domain;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoTest {
    static Stream<Arguments> parseLotto1To6AsList() {
        return Stream.of(
            Arguments.of(
                    List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))
            )
        );
    }

    static Stream<Arguments> parseLottoInvalid() {
        return Stream.of(
            Arguments.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5))),
            Arguments.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6), LottoNumber.of(7)))
        );
    }

    @ParameterizedTest(name = "create from list: {arguments}")
    @MethodSource("parseLotto1To6AsList")
    public void createFromList(List<LottoNumber> numbers) {
        assertThat(Lotto.of(numbers))
                .isEqualTo(Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))));
        assertThat(Lotto.of(numbers).collect()).hasSize(6);
        assertThat(Lotto.of(numbers).collect())
                .containsExactly(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6));
    }

    @ParameterizedTest(name = "create failed from list: {arguments}")
    @NullAndEmptySource
    @MethodSource("parseLottoInvalid")
    public void createFailedFromList(List<LottoNumber> numbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(numbers))
                .withMessageContaining("size of numbers must be");
    }

    @Test
    @DisplayName("create failed cause by duplicated numbers: {arguments}")
    public void createFailedByDuplicatedNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(1))))
                .withMessageContaining("duplicated numbers");
    }

    static Stream<Arguments> parseMatched() {
        return Stream.of(
                Arguments.of(
                        Lotto.of(
                                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))),
                        6),
                Arguments.of(
                        Lotto.of(
                                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                                        LottoNumber.of(43), LottoNumber.of(44), LottoNumber.of(45))),
                        3),
                Arguments.of(
                        Lotto.of(
                                List.of(LottoNumber.of(40), LottoNumber.of(41), LottoNumber.of(42),
                                        LottoNumber.of(43), LottoNumber.of(44), LottoNumber.of(45))),
                        0)
        );
    }

    @ParameterizedTest(name = "check matched count with FixedLotto(1,2,3,4,5,6): {arguments}")
    @MethodSource("parseMatched")
    public void matchedCount() {
        final Lotto testLotto = Lotto.of(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
        assertThat(testLotto.matchedCount(testLotto)).isEqualTo(6);
    }
}
