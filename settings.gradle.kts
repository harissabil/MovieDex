@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
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

rootProject.name = "MovieDex"
include(":app")
include(":feature:movie:data")
include(":feature:movie:domain")
include(":feature:movie:ui")
include(":feature:movie_detail:data")
include(":feature:movie_detail:domain")
include(":feature:movie_detail:ui")
include(":core:network")
include(":core:common")
include(":core:feature_api")
include(":feature:movie_favorite")
include(":core:database")
