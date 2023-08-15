plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
//    id("com.google.gms.google-services")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
                implementation("io.ktor:ktor-client-serialization:2.0.2")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}
dependencies {
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.0")
}
