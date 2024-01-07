plugins {
    alias(libs.plugins.androidDynamicFeature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}
android {
    namespace = "com.harissabil.moviedex.feature.movie_favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
}

dependencies {
    implementation(project(":core:feature_api"))
    implementation(project(":core:database"))
    implementation(project(":core:common"))
    implementation(project(":feature:movie_detail:ui"))

    implementation(project(":app"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bomb))
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bomb))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.compose.bomb))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bomb))
    debugImplementation(libs.bundles.compose.debug)

    implementation(libs.timber)

    debugImplementation(libs.squareup.leakcanary.android)

    implementation(libs.bundles.coil)

    implementation(libs.bundles.hilt)
    ksp(libs.bundles.hilt.compiler)
}