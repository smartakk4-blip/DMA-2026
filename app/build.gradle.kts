plugins {
    id("com.android.application")
}

android {
    namespace = "ru.kulikov.pr5"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.kulikov.pr5"
        minSdk = 26
        // как в ПР4: на 35 включается обязательный edge-to-edge
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
}
