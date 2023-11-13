plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    kotlin("plugin.serialization") version "1.6.0"
}

android {
    namespace = "ajman.shayan.joisty"
    compileSdk = 33

    defaultConfig {
        applicationId = "ajman.shayan.joisty"
        minSdk = 24
//        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
//    packaging {
//        resources {
//            excludes += "META-INF/DEPENDENCIES"
//            excludes += "META-INF/INDEX.LIST"
//            excludes += "META-INF/LICENSE-GPL-2.txt"
//            excludes += "META-INF/LICENSE-GPL-3.txt"
//            excludes += "META-INF/LICENSE-LGPL-2.1.txt"
//            excludes += "META-INF/LICENSE-LGPL-2.txt"
//            excludes += "META-INF/LICENSE-LGPL-3.txt"
//            excludes += "META-INF/LICENSE-W3C-TEST"
//            excludes += "org/bouncycastle/x509/CertPathReviewerMessages.properties"
//            excludes += "org/bouncycastle/x509/CertPathReviewerMessages_de.properties"
//        }
//    }
}

//configurations.all {
//    resolutionStrategy {
//        force("org.bouncycastle:bcprov-jdk14:1.38")
//    }
//}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.room:room-runtime:2.4.1")
    kapt("androidx.room:room-compiler:2.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

////    implementation("org.bouncycastle:bcprov-jdk14:1.76")
//    implementation("org.xhtmlrenderer:flying-saucer-pdf:9.2.0") {
////        exclude(group = "org.bouncycastle")
//    }
//    implementation("org.xhtmlrenderer:flying-saucer-core:9.2.0") {
////        exclude(group = "org.bouncycastle")
//    }
//    implementation("com.lowagie:itext:2.1.7")

//    implementation("org.apache.pdfbox:pdfbox:2.0.30")
//    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.0.0")
//    implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
//    implementation("org.thymeleaf:thymeleaf-spring5:3.1.2.RELEASE")

//    implementation("com.itextpdf:itext7-core:7.1.15")

//    implementation("com.github.PDDStudio:WebView2PDF:1.0.5")

//    implementation("com.itextpdf:itextpdf:5.5.13.2")
//    implementation("org.scilab.forge:jlatexmath:1.0.7")

}