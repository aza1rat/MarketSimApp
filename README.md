# MarketSimApp
Продуктовое название **Arvo**  
Приложение нацелено на функционал интернет магазина
## Roadmap
- Авторизация
- Регистрация
- Splash screen
- Главная
    - Список товаров по категории
    - Список товаров в последней просмотренной категории
    - Поиск
      - История поиска
- Категории
- Список товаров
- Карточка товара
- Корзина
    - Оформление (ввод адреса).
    - Отслеживание заказа
- Wishlist/Желаемое
- Профиль
    - Редактирование профиля
    - Заказы
    - логаут
## Текущий этап
**Добавление splash screen, авторизации и регистрации ⏳**  
[Текущие задачи ⏳](https://github.com/aza1rat/MarketSimApp/issues?q=is%3Aissue+state%3Aopen+label%3Aauthorization)  
  
[Добавлена навигация auth ✅](https://github.com/aza1rat/MarketSimApp/pull/32)  
[Хранение сессии и шифрация токена авторизации ✅](https://github.com/aza1rat/MarketSimApp/pull/33)  
[Добавлен ktor-клиент ✅](https://github.com/aza1rat/MarketSimApp/pull/35)  
[Переиспользуемые compose элементы с экранов авторизации ✅](https://github.com/aza1rat/MarketSimApp/pull/39)
## Технологии и архитектура
### Архитектура
**Архитектурный паттерн** - MVI  
**DI** - Hilt  
**Многомодульность** - feature + core
### Навигация и интерфейс
**Навигация** - Navigation Compose  
**UI** - Jetpack Compose
### Асинхронность
Kotlin Coroutines & Coroutines Flow
### Работа с REST API
**Сетевые запросы** - Ktor Client  
**Сериализация JSON** - Kotlin Serialization
### Хранение токена сессии
**Шифрование** - javax.crypto + Keystore  
**Хранение** - DataStore Preferences
### CI
**Проверки веток при мердже** - [branch_rules](https://github.com/aza1rat/MarketSimApp/actions/workflows/branch_rules.yml) + [block-branch-merge](https://github.com/aza1rat/MarketSimApp/actions/workflows/block-branch-merge.yml)  
**Статический анализ кода** - [detekt-check](https://github.com/aza1rat/MarketSimApp/actions/workflows/detekt-check.yml)

### Сборка
**Convention-плагины** - [build-logic/convention](https://github.com/aza1rat/MarketSimApp/tree/main/build-logic/convention)

### Документация
[Правила работы с ветками](https://github.com/aza1rat/MarketSimApp/blob/main/docs/Branching.md)  
[Архитектура навигации](https://github.com/aza1rat/MarketSimApp/blob/main/docs/Navigation%20Architecture.md)  
[Требования к Gradle структуре](https://github.com/aza1rat/MarketSimApp/blob/main/docs/specs/%D0%A2%D1%80%D0%B5%D0%B1%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%20%D0%BA%20Gradle%20%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D0%B5.md)  
[Взаимодействие с REST API](https://github.com/aza1rat/MarketSimApp/blob/develop/docs/Data%20layer%20description.md)