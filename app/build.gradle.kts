plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.devtools.ksp")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.farzin.checklisttodo"
    compileSdk = 34

    lint{
        checkReleaseBuilds= false
        abortOnError= false
    }

    defaultConfig {
        applicationId = "com.farzin.checklisttodo"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //hilt di
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //compose navigation
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    //animation
    implementation ("com.airbnb.android:lottie-compose:6.3.0")

    //system ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //room
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // persian calendar
//    implementation ("com.github.samanzamani:PersianDate:1.7.1")
//    implementation("ir.huri:jalalicalendar:2.0.0")
//    implementation ("com.github.hasin-neobank:android-persian-picker:1.2.4")
    implementation ("com.github.aliab:Persian-Date-Picker-Dialog:1.8.0")

    // circular progress bar
    implementation ("com.github.hitanshu-dhawan:CircularProgressBar-Compose:1.0.0-rc01")

    // time picker
//    implementation ("com.github.commandiron:WheelPickerCompose:1.1.11")
//    implementation("com.marosseleng.android:compose-material3-datetime-pickers:0.7.2")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:core:1.2.1")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:clock:1.2.1")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    // threetenapb for date api 26 or lower
//    implementation("com.jakewharton.threetenabp:threetenabp:1.2.2")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")
}