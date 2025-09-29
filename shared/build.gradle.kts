import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kspMultiplatform)
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
        browser()
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
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        // ✅ Define an XCFramework
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
    add("kspJvm", libs.kotlininject.compiler)
    add("kspWasmJs", libs.kotlininject.compiler)
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
