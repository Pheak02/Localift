plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.local.locallift"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.local.locallift"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    signingConfigs {
//        create("release") {
//            storeFile = file("/Users/macos/Desktop/VisitMe.jks")
//            storePassword = "Welcome.01"
//            keyAlias = "VisitMe"
//            keyPassword = "Welcome.01"
//        }
//    }

    buildTypes {
        release {
//            signingConfig = signingConfigs.getByName("release")
            // make output file is smaller, prevent our APK file to be covert or decompiled
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    flavorDimensions += "LocalLift"
//    productFlavors {
//    create("dev") {
//        dimension = "LocalLift"
//        applicationId = "com.local.locallift.dev"
//        resValue("string", "app_name", "LocalLift Test" )
//        buildConfigField("String", "apiBaseUrl", "\"https://locallift-aeb0d-default-rtdb.firebaseio.com/test\"")
//
//    }
//    create("prd") {
//        dimension = "LocalLift"
//        applicationId = "com.local.locallift"
//        resValue("string", "app_name", "LocalLift" )
//        buildConfigField("String", "apiBaseUrl", "\"https://locallift-aeb0d-default-rtdb.firebaseio.com/\"")
//
//    }
//    create("premium") {
//        buildConfigField("Bool", "isPremium", "true")
//
//    }
//    create("free") {
//        buildConfigField("Bool", "isFree", "false")
//
//    }

//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
//        buildConfig = true
    }
}

dependencies {
    // Core libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.2")

    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Coroutines for background tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Lifecycle components for LiveData and ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Logging Interceptor for Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Firebase dependencies
    implementation("com.google.firebase:firebase-firestore:24.7.0")
    implementation("io.coil-kt:coil:2.2.2")
    implementation("com.google.firebase:firebase-auth-ktx:23.1.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.1")
    implementation("com.google.firebase:firebase-database:21.0.0")


    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}
