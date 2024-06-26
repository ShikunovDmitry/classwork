@All @Withdrawal
Feature: Снятие денег со счета

  @Success
  Scenario: Успешное снятие денег
    Given на счете пользователя имеется 120000 рублей
    When пользователь снимает 20000 рублей
    Then на счете остается 100000 рублей
    And я доволен

  @Negative
  Scenario: Снятия денег со счета - недостаточно средств
    Given на счете пользователя имеется 120 рублей
    When пользователь снимает 140 рублей
    Then появляется предупреждение "На счете недостаточно денег"
    And Ты доволен
#    And новый степ

  @Negative
  Scenario: Снятие денег со счета - не изменяет баланс при невозможности снятия
    Given на счете пользователя имеется 120 рублей
    When пользователь снимает 140 рублей
    Then на счете остается 120 рублей
    And Наталья Петровна довольна

  @DataDriven @Negative
  Scenario Outline: Снятия денег со счета - недостаточно средств <withdrawal>
    Given на счете пользователя имеется <balance> рублей
    When пользователь снимает <withdrawal> рублей
    Then появляется предупреждение "<message>"
    Examples:
      | balance | withdrawal | message                     |
      | 100     | 120        | На счете недостаточно денег |
      | 50      | 60         | На счете недостаточно денег |
      | 2000    | 10000      | На счете недостаточно денег |