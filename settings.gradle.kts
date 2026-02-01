enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

rootProject.name = "MyRecipy"

include(":app")
include(":core:data")
include(":core:database")
include(":core:model")
include(":core:ui")
include(":core:navigation")
include(":feature:shell:editor:api")
include(":feature:shell:editor:impl")
include(":feature:shell:report:api")
include(":feature:shell:report:impl")
include(":feature:shell:settings:api")
include(":feature:shell:settings:impl")
include(":feature:shell:calendar:api")
include(":feature:shell:calendar:impl")
include(":feature:shell:history:api")
include(":feature:shell:history:impl")
include(":core:network")
