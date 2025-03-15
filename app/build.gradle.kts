import org.jetbrains.kotlin.konan.properties.Properties

//import java.util.Properties

//val properties: Properties = Properties().apply {
//    rootProject.file("local.properties").reader().use(::load)
//}


//properties.load(
//    project.rootProject
//        .file("local.properties")
//        .inputStream()
//)

val prop: Properties = Properties()

prop.load(
    project.rootProject
        .file("local.properties")
        .inputStream()
)


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
// asdded
    alias(libs.plugins.android.dagger.hilt)
//   alias(libs.plugins.ksp)
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "cl.rodrigojavier101.electriccarcharger"
    compileSdk = 35

    defaultConfig {
        applicationId = "cl.rodrigojavier101.electriccarcharger"
        minSdk = 24
        targetSdk = 35
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
    flavorDimensions += "version"
    productFlavors {

        create("Charger") {
            applicationIdSuffix = ".Charger"
            versionNameSuffix = "1_Charger"

            resValue(
                "string",
                "key_map",
                prop.getProperty("API_MAP_KEY")

            )

            resValue(
                "string",
                "app_name",
                "Charger"
            )

            buildConfigField(
                "String",
                "URL_BASE",
                prop.getProperty("URL_BASE")
            )

            buildConfigField(
                "String",
                "URL_ENDPOINT",
                prop.getProperty("URL_ENDPOINT")
            )

            buildConfigField(
                "String",
                "COMPANY_ID",
                prop.getProperty("COMPANY_ID")
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
        //
        buildConfig = true
        viewBinding = true
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
// // // // //
    // TEST
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /* TESTS ADDED */
    testImplementation(libs.mokk)
    testImplementation(libs.mockitocore)
    testImplementation(libs.mockitoinline)

    /*splashscreen*/
    implementation(libs.androidx.splash)

    /*google map*/
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.android.maps.utils)

    /*navigation NO CAMBIAR LA VERSION "2.6.0" **************/
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.fragmentktx)
    implementation(libs.androidx.legacy)

    /*picasso*/
    implementation(libs.androidx.picasso)
    // constraint layout
//    implementation(libs.androidx.constraintlayout)
    //    retrofit
    implementation(libs.androidx.retrofit)
    implementation(libs.androidx.retrofitgson)
// okhttp
    implementation(libs.okhttp)
    implementation(libs.googlegson)
    implementation(libs.okhttp.interceptor)

    //    coroutines
    implementation(libs.androidx.coroutines)
    implementation(libs.androidx.coroutinesandroid)

    //   coroutine lifecycle scopes
    implementation(libs.androidx.activityktx)
    implementation(libs.androidx.lifecyclesextensions)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // KTX - Viewmodel Y Livedata NO ACTUALIZAR: 2.5.1
    implementation(libs.androidx.lifecycle.livedata)
    //    data store
    implementation(libs.androidx.datastore)
    //Room
    implementation(libs.room)
    // kapt genera el codigo para la base de datos
    kapt(libs.roomkaptcompiler)
    implementation(libs.androidx.room.runtime)

    //Hilt
    implementation(libs.dagger.google)
    implementation(libs.androidx.hilt.navigation.compose.v120)
    annotationProcessor(libs.androidx.google.compiler)
    kapt(libs.androidx.google.dagger.kapt.android)
    kapt(libs.androidx.google.compiler)
    kapt(libs.androidx.hilt.compiler.v120)

    // shimmer
    implementation(libs.shimmer)

//   COMPOSE
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

}

kapt { correctErrorTypes = true }