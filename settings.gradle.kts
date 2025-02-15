pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "myosin"
//include(":library")
include(":unit-model")
include(":geo-model")
include(":directions-model")
include(":address-model")
include(":place-model")
