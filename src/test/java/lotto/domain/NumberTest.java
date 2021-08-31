package lotto.domain;

import static lotto.domain.Number.generateNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    @DisplayName("로또 번호에 1이상 45이하의 수가 들어오지 않으면 Exception이 발생되어야 한다.")
    void lottoSaveNumberRangeTest(int input) {

        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Number(input))
            .withMessageMatching("로또 번호는 1이상 45이하의 수만 들어올 수 있다.");
    }

    @Test
    @DisplayName("입력한 당첨번호를 List로 생성할 수 있다.")
    void lottoWinSaveTest() {

        // given
        String[] input = "1, 2, 3, 4, 5, 6".split(", ");
        List<Number> expected = Arrays.asList(1, 2, 3, 4, 5, 6)
            .stream()
            .map(Number::new)
            .collect(Collectors.toList());

        // when
        List<Number> inputs = generateNumbers(input);

        // then
        assertThat(inputs).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2,3,4 ,5,6", "1,2,3,4,5,6", "1,   2,3   ,4,5, 6  "})
    @DisplayName("공백이 마음데로 지정된 입력을 받아도 당첨번호를 반환할 수 있다.")
    void lottoGenerateNumberTest(String input) {

        // given
        List<Number> expected = Arrays.asList(1, 2, 3, 4, 5, 6)
            .stream()
            .map(Number::new)
            .collect(Collectors.toList());
        String[] inputArray = input.split(",");

        // when
        List<Number> inputs = generateNumbers(inputArray);

        // then
        assertThat(inputs).isEqualTo(expected);
    }

}
