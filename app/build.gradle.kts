plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.harissabil.moviedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.harissabil.moviedex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    dynamicFeatures += setOf(":feature:movie_favorite")
}

dependencies {
    implementation(project(":feature:movie:data"))
    implementation(project(":feature:movie:domain"))
    implementation(project(":feature:movie:ui"))

    implementation(project(":feature:movie_detail:data"))
    implementation(project(":feature:movie_detail:domain"))
    implementation(project(":feature:movie_detail:ui"))

    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:feature_api"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bomb))
    implementation(libs.bundles.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.timber)

    androidTestImplementation(platform(libs.compose.bomb))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.bundles.compose.debug)

    debugImplementation(libs.squareup.leakcanary.android)

    implementation(libs.bundles.hilt)
    ksp(libs.bundles.hilt.compiler)
}