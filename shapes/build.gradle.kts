import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    id("com.vanniktech.maven.publish")
}

kotlin {
    androidLibrary {
        minSdk = 21
        compileSdk = 36
        namespace = "com.kyant.shapes"
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    jvm("desktop")

    js(IR) {
        browser()
    }
    wasmJs {
        browser()
    }

    macosArm64()
    iosArm64("iosArm64")
    iosSimulatorArm64("iosSimulatorArm64")

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.ui)
        }

        all {
            languageSettings.enableLanguageFeature("ContextParameters")
        }
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates("io.github.kyant0", "shapes", "1.1.1")

    pom {
        name.set("Shapes")
        description.set("iOS-like shapes for Compose Multiplatform")
        inceptionYear.set("2026")
        url.set("https://github.com/Kyant0/Shapes")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("Kyant0")
                name.set("Kyant")
                url.set("https://github.com/Kyant0")
            }
        }
        scm {
            url.set("https://github.com/Kyant0/Shapes")
            connection.set("scm:git:git://github.com/Kyant0/Shapes.git")
            developerConnection.set("scm:git:ssh://git@github.com/Kyant0/Shapes.git")
        }
    }
}
