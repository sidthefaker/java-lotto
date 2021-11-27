# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 로또(자동)
## 기능 요구사항
* 로또 발급
  * 로또 1장에는 6개의 숫자를 뽑는다.
  * 로또에서 사용하는 숫자는 1 ~ 45
* 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
  * 로또 1장의 가격은 1000원이다.
  * 예외처리: 0 혹은 1000 미만의 금액을 입력했을때, 올바르지 않은 입력으로 처리한다.
  * 예외처리: 거스름돈은? 수익률까지 생각하면 처리가 곤란함. 딱 맞는 금액만 받는다.
* 당첨 등수는 갯수에 의해 결정된다.
  * 6개 모두 동일하면 1등으로 당첨금은 2,000,000,000원
  * 5개 숫자가 동일하면 2등으로 당첨금은 1,500,000원
  * 4개 숫자가 동일하면 3등으로 당첨금은 50,000원
  * 3개 숫자가 동일하면 4등으로 당첨금은 5,000원
* 구입 금액대비 당첨금의 수익률을 제공한다.

## 프로그래밍 요구사항
* 모든 기능을 TDD로 구현, 단위 테스트가 존재해야 함(단, UI(System.out, System.in) 로직은 제외)
  * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분
  * UI 로직을 InputView, ResultView와 같은 클래스로 분리
* indent(인덴트, 들여쓰기) depth를 1까지만 허용
* 함수(또는 메소드)의 길이는 15라인 이내
  * 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현
* 자바 코드 컨벤션을 지키면서 프로그래밍
* else 사용 금지

## 기능 목록 및 commit 로그 요구사항
* README.md 파일에 구현할 기능 목록 정리
* git의 commit 단위는 정리한 기능 목록 단위로 추가

# 1단계 - 문자열 덧셈 계산기
## 기능 요구사항
* 문자열 입력을 받아 숫자를 구분하고 합을 반환
* 숫자 구분은 쉼표(,) 또는 콜론(:)
* 커스텀 구분자를 지정. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자
* 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw

## 프로그래밍 요구사항
* indent(들여쓰기) depth를  1단계
* 메소드의 크기가 최대 10라인
* method가 한 가지 일만 하도록 최대한 작게
* else 사용 금지