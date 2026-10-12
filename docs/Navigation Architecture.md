# Navigation Architecture

В проекте используется **Navigation Compose** с type-safe navigation.

## 1. Общая структура

Навигационный граф приложения собирается в `app` — composition root приложения.

Общая структура:

```text
app
└── MarketSimNavHost
    ├── Auth navigation graph
    ├── Cart navigation
    ├── Category navigation
    ├── Main navigation
    ├── Product navigation
    ├── Profile navigation
    └── Tracking navigation
```

`MarketSimNavHost` отвечает за композицию общего navigation graph, а отдельные feature-модули — за регистрацию своих destinations.

---

## 2. Type-safe Navigation

Для маршрутов используются `@Serializable`-классы:

```kotlin
@Serializable
data object LoginScreenRoute

@Serializable
data object RegisterScreenRoute
```

Destination регистрируется с использованием type-safe API:

```kotlin
composable<LoginScreenRoute> {
    LoginScreen()
}
```

Для перехода используется типизированный route вместо строкового значения:

```kotlin
fun NavController.navigateToRegister() {
    navigate(route = RegisterScreenRoute)
}
```

Таким образом, маршруты представлены Kotlin-типами, а не строками.

---

## 3. Регистрация destinations

Регистрацию destinations отдельной feature следует инкапсулировать в функции расширения `NavGraphBuilder`.

Например:

```kotlin
fun NavGraphBuilder.loginScreenNavigation(
    onNavigateToMain: () -> Unit
) {
    composable<LoginScreenRoute> {
        LoginScreen()
    }
}
```

Это позволяет не размещать детали конкретной feature непосредственно в корневом `NavHost`.

---

## 4. Композиция корневого графа

`MarketSimNavHost` объединяет navigation graphs отдельных feature:

```kotlin
@Composable
fun MarketSimNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AuthScreenRoute
    ) {
        authScreenNavigation()
        cartScreenNavigation()
        categoryScreenNavigation()
        mainScreenNavigation()
        productScreenNavigation()
        profileScreenNavigation()
        trackingScreenNavigation()
    }
}
```

На этом уровне описывается **структура приложения**, а не внутренняя реализация отдельных feature.

---

## 5. Nested Navigation Graphs

Для группы логически связанных экранов используется nested navigation graph.

Например, авторизация объединяет `Login` и `Register`:

```kotlin
fun NavGraphBuilder.authScreenNavigation() {
    navigation<AuthScreenRoute>(
        startDestination = LoginScreenRoute
    ) {
        loginScreenNavigation()
        registerScreenNavigation()
    }
}
```

В данном случае:

* `AuthScreenRoute` — route navigation graph, а не экран;
* `LoginScreenRoute` — стартовый destination этого graph;
* `RegisterScreenRoute` — другой destination этого graph.

Если route используется как `startDestination`, он должен быть зарегистрирован в navigation graph.

---

## 6. Границы ответственности модулей

### `feature:<name>`

Содержит публичный контракт и реализацию feature.

* UI;
* ViewModel;
* navigation registration;
* navigation graph конкретной feature;
* внутренние implementation details.


### `core:navigation`

Содержит только общую навигационную инфраструктуру.

`core:navigation` **не должен зависеть от feature-модулей**.

Зависимость должна быть направлена от конкретного приложения к feature, а не наоборот:

```text
app
 ├── feature:auth
 └── core:navigation

core:navigation
      ↓
   no features
```

---

## 7. Composition Root

`app` является composition root приложения.

Именно `app` знает:

* какие feature входят в приложение;
* какие navigation graphs необходимо добавить;
* какой graph является стартовым;
* как объединяются отдельные части navigation graph.

Feature не должна знать о полном navigation graph приложения.

Например, `auth` отвечает за:

```kotlin
authScreenNavigation()
```

но не должен самостоятельно добавлять `cart`, `profile` или другие feature в корневой graph.

---

## 8. Navigation и UI

Navigation registration является связующим слоем между navigation graph и экраном:

```kotlin
fun NavGraphBuilder.loginScreenNavigation(
    navController: NavController
) {
    composable<LoginScreenRoute> {
        LoginScreenRouteContent()
    }
}
```

Сам экран не должен отвечать за регистрацию себя в `NavHost`.

Условно ответственность разделена так:

```text
NavGraphBuilder
      │
      ▼
Navigation registration
      │
      ▼
Route
      │
      ▼
Screen content
      │
      ▼
ViewModel / UI state
```

---

## 9. ViewModel и navigation entry point

ViewModel создаётся внутри соответствующего navigation destination:

```kotlin
@Composable
fun NavGraphBuilder.loginScreenNavigation() {
    composable<LoginScreenRoute>(){
    val viewModel: LoginViewModel = hiltViewModel()

    // ...
 }
}
```

Navigation layer передаёт экрану необходимые зависимости и связывает destination с его UI.

При использовании `hiltViewModel()` ViewModel получает `ViewModelStoreOwner`, связанный с текущим navigation destination.

---

## 10. Добавление нового экрана

При добавлении нового экрана необходимо:

1. Создать type-safe route:

```kotlin
@Serializable
data object NewScreenRoute
```

2. Создать функцию регистрации destination:

```kotlin
fun NavGraphBuilder.newScreenNavigation() {
    composable<NewScreenRoute> {
        NewScreen()
    }
}
```

3. Добавить регистрацию в соответствующий feature graph.

4. Если экран относится к отдельной логической группе, добавить его в соответствующий nested navigation graph.

5. Если это новая feature, добавить её graph в корневой `MarketSimNavHost`.

---

## 11. Структура навигационного графа

Итоговый navigation graph приложения имеет следующую структуру:

```text
MarketSimNavHost
│
├── AuthScreenRoute
│   ├── LoginScreenRoute
│   └── RegisterScreenRoute
│
├── CartScreenRoute
│   └── ...
│
├── CategoryScreenRoute
│   └── ...
│
├── MainScreenRoute
│   └── ...
│
├── ProductScreenRoute
│   └── ...
│
├── ProfileScreenRoute
│   └── ...
│
└── TrackingScreenRoute
    └── ...