import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

android {
    namespace = "com.kyant.shapes.app"
    compileSdk = 36

    defaultConfig {
        targetSdk = 36
        minSdk = 23

        applicationId = "com.kyant.shapes.app"
        versionName = "1.0.0"
        versionCode = 1
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xlambdas=class",
        )
    }
}

dependencies {
    implementation(project(":shapes"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
}
