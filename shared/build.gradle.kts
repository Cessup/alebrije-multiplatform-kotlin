import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kspMultiplatform)
    alias(libs.plugins.sqldelight)
    kotlin("plugin.serialization") version "1.9.24"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                sourceMaps = false
            }
        }
        binaries.executable()
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                // Common dependencies for all platforms
                implementation(libs.kotlininject.runtime)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.runtime)
                implementation(libs.kotlinx.datetime)
            }
        }

        val jvmMain by getting {
            dependencies{
                implementation(libs.ktor.client.cio)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.android.driver)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.native.driver)
            }
        }

        // Connect iOS targets to iosMain
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val xcFramework = XCFramework()

        listOf(
            targets.getByName("iosX64"),
            targets.getByName("iosArm64"),
            targets.getByName("iosSimulatorArm64")
        ).forEach {
            val framework = (it as KotlinNativeTarget).binaries.getFramework("DEBUG")
            xcFramework.add(framework)
        }

        // KSP Common sourceSet
        sourceSets.named("commonMain").configure {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
    }
}

android {
    namespace = "com.cessup.alebrije_multiplatform_kotlin.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

// KSP Tasks
dependencies {
    add("kspCommonMainMetadata", libs.kotlininject.compiler)
}


// Trigger Common Metadata Generation from Native tasks
tasks.matching { it.name.startsWith("ksp") && it.name != "kspCommonMainKotlinMetadata" }.configureEach {
    dependsOn("kspCommonMainKotlinMetadata")
}

tasks.register<Copy>("copyXCFrameworkToIos") {
    dependsOn("assembleXCFramework")
    from(buildDir.resolve("XCFrameworks/debug/shared.xcframework"))
    into(rootProject.file("iosApp/Frameworks"))
}
