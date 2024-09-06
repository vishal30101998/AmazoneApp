plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.amazonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.amazonapp"
        minSdk = 33
        //noinspection OldTargetApi
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
//    implementation ("com.google.android.material:material:1.0.0")
//    implementation ("androidx.navigation:navigation-fragment:2.4.1")
//    implementation ("androidx.navigation:navigation-ui:2.4.1")
//    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
//    implementation ("com.google.firebase:firebase-auth:21.0.1")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //sliderview
//    implementation (libs.autoimageslider)
//    implementation (libs.autoimageslider.v140appcompat)
//    implementation (libs.glide)
//    implementation (libs.android.image.slider)
//    implementation ("com.github.smarteist:autoimageslider:1.3.9")
//    implementation ("com.github.bumptech.glide:glide:4.11.0")
//menu drawer
//    implementation ("com.google.android.material', name: 'material', version: '1.1.0-alpha05")
//
//    //circleimageview
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
//
//    //firebase recylcer adapter
 //   implementation ("com.firebaseui:firebase-ui-database:6.0.2")
    implementation ("com.firebaseui:firebase-ui-database:8.0.2")
//
//    //razorpay
//    implementation ("com.razorpay:checkout:1.6.17")
}
