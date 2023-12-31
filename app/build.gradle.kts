import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

var properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())
val kakao_native_key: String = properties.getProperty("kakao_native_key")

android {
    namespace = "com.zenefit.zenefit_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zenefit.zenefit_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "dev_api", properties.getProperty("dev_api"))
        buildConfigField ("String", "KAKAO_NATIVE_KEY", properties.getProperty("KAKAO_NATIVE_KEY"))
        manifestPlaceholders["kakao_native_key"] = kakao_native_key

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

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /** Glide */
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    implementation ("jp.wasabeef:glide-transformations:4.3.0")

    /** MVVM */
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.activity:activity-ktx:1.8.0")

    /** Retrofit */
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    /** Hilt **/
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")

    /** Kakao SignIn **/
    implementation ("com.kakao.sdk:v2-user:2.14.0")

    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")

    /** Lottie **/
    implementation ("com.airbnb.android:lottie:5.0.2")
}