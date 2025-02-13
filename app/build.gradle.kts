plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "quotebox.daily_suvichar_for_you"
    compileSdk = 34

    defaultConfig {
        applicationId = "quotebox.daily_suvichar_for_you"
        minSdk = 24
        targetSdk = 34
        versionCode = 58
        versionName = "5.8"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.crashlytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.picasso)
    implementation(libs.styleabletoast)
    implementation(libs.volley)


    implementation(platform(libs.firebase.bom))
    implementation(libs.play.services.auth)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
    implementation(libs.lottie)
    implementation(libs.ucrop)
    implementation(libs.photoview)
    implementation(libs.play.services.ads)
    implementation(libs.firebase.dynamic.links)
    implementation("com.facebook.android:audience-network-sdk:6.10.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    implementation("com.razorpay:checkout:1.6.38")

    implementation("com.onesignal:OneSignal:[5.0.0, 5.99.99]")
    implementation("com.arthenica:ffmpeg-kit-full:6.0-2")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    /*implementation("com.onesignal:OneSignal:5.1.26")*/
}