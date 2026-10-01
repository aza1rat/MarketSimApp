pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "market-sim-app-root"
include(":app")
include(":core:network")
include(":core:database")
include(":core:ui")
include(":core:mvi")
include(":core:navigation")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:cart:api")
include(":feature:cart:impl")
include(":feature:category:api")
include(":feature:category:impl")
include(":feature:main:api")
include(":feature:main:impl")
include(":feature:product:api")
include(":feature:product:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:tracking:api")
include(":feature:tracking:impl")
