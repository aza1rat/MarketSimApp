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
include(":core:session-api")
include(":core:datastore")
include(":feature:auth")
include(":feature:cart")
include(":feature:category")
include(":feature:main")
include(":feature:product")
include(":feature:profile")
include(":feature:tracking")
