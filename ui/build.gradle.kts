plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        freeCompilerArgs = freeCompilerArgs +
                "-opt-in=androidx.paging.ExperimentalPagingApi" +
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api" +
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi" +
                "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.code.gson:gson:2.10")

    implementation("androidx.compose.material:material:1.6.1")

    //Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("com.github.skydoves:landscapist-coil:1.5.2")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //Dagger-Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //ViewModel Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$2.4.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")

    //Youtube Player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    //Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.paging:paging-compose:3.2.1")

    implementation(project(":domain"))
    implementation(project(":util"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}