plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    //  id("com.google.devtools.ksp")
}

android {
    namespace = "com.joni.videocallassignment"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.joni.videocallassignment"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.github.ZEGOCLOUD:zego_uikit_prebuilt_call_android:+")

    implementation("androidx.compose.ui:ui-viewbinding:1.5.4")

    val room_version = "2.8.4"

   // implementation("androidx.room:room-runtime:$room_version")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
   // ksp("androidx.room:room-compiler:$room_version")


    val nav_version = "2.9.6"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))


    implementation("com.google.firebase:firebase-auth")

    implementation("com.google.firebase:firebase-firestore")

// Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
}