pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
//            url = uri("https://my.pspdfkit.com/maven")
            url = uri("https://pspdfkit.jfrog.io/artifactory/pspdfkit-android")
        }
    }
}

rootProject.name = "App 1"
include(":app")
 