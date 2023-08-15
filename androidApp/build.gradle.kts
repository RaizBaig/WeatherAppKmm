plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.weatherapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.weatherapp.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
//        compose = true
        dataBinding = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.4.7"
//    }
    packaging {
        resources {
            excludes += "/META-INF"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    //Android UI
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Nav Graph UI
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    //Ktx
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    //Firebase
    implementation("com.google.firebase:firebase-common-ktx:20.3.3")
    implementation ("com.google.firebase:firebase-bom:32.2.2")
//    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.0")

    //Gson
    implementation ("com.google.code.gson:gson:2.9.0")
}