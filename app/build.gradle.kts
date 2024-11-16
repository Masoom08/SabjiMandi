plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "st.masoom.sabjimandi"
    compileSdk = 34

    defaultConfig {
        applicationId = "st.masoom.sabjimandi"
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
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.compose.material:material:1.5.0")
    implementation ("androidx.compose.ui:ui:1.5.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation ("androidx.compose.foundation:foundation:1.5.0")
    implementation("androidx.benchmark:benchmark-macro:1.3.1")
    implementation("androidx.leanback:leanback:1.0.0")


    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")


    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.firebase:firebase-storage:21.0.1")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("androidx.compose.foundation:foundation-layout-android:1.5.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("com.razorpay:checkout:1.6.40")
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("com.google.firebase:firebase-auth:22.1.1")

}
