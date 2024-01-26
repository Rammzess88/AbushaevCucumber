# language: ru

@all
Функция: Первый тест на кукумбере

  Предыстория:
    * Пользователь инициализирует драйвер и  открывает страницу "http://localhost:8080/food"
    * Пользователь проверяет, что название таблицы "Список товаров"
    * Пользователь проверяет, что наименования столбцов таблицы:
      | #            |
      | Наименование |
      | Тип          |
      | Экзотический |

  @Test1
  Сценарий: Кейс с фруктом
    * Пользователь добавлет товар:
      | A№@№@$ |
      | Фрукт  |
      | exotic |
    * Пользователь проверяет, что товар "A№@№@$" присутствует в таблице

  @Test2
  Сценарий: Кейс с овощем
    * Пользователь добавлет товар:
      | Томат12345! |
      | Овощ        |
      | noExotic    |
    * Пользователь проверяет, что товар "Томат12345!" присутствует в таблице
    * Пользователь закрывает драйвер