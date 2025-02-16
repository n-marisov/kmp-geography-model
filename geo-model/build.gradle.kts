import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "ru.myosin"
version = "1.0.0"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    androidNativeArm64()
    androidNativeX64()

    linuxX64 {
        binaries {
            executable()
        }
    }
    linuxArm64 {
        binaries {
            executable()
        }
    }

    js {
        browser {  }
        nodejs {  }
        binaries.executable()
    }
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs{
        browser()
        nodejs()
        binaries.executable()
    }

    mingwX64()



    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jsMain by getting{
            dependencies {

            }
        }

        val jsTest by getting{
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "ru.myosin.geo.model"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)

    signAllPublications()

    coordinates("ru.myosin", "geo-model", version.toString())

    pom {
        name = "Myosin geography models"
        description = "A set of geographical models of the Myosin library."
        inceptionYear = "2025"
        url = "https://github.com/n-marisov/kmp-geography-model"

        developers {
            developer {
                name = "Nikolai Marisov"
                email = "marisov123456789@mail.ru"
                organizationUrl = "https://github.com/n-marisov"
            }
        }

        licenses {
            license {
                name = "The Apache Software License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }

        scm {
            url = "https://github.com/n-marisov/kmp-geography-model/tree/master/geo-model"
            connection = "scm:git:git://github.com/n-marisov/kmp-geography-model.git"
            developerConnection = "scm:git:ssh://github.com/n-marisov/kmp-geography-model.git"
        }
    }
}
