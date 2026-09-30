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
include(":core:navigation")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:register:api")
include(":feature:register:impl")
