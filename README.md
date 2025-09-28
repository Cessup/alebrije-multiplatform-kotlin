# Alebrije App

[![License](https://img.shields.io/github/license/Cessup/alebrije-multiplatform-kotlin
)](https://opensource.org/licenses/Apache-2.0)
[![Workflow](https://img.shields.io/github/actions/workflow/status/Cessup/alebrije-multiplatform-kotlin/.github%2Fworkflows%2Falebrije-kmp-ci.yml
)](https://github.com/Cessup/alebrije-multiplatform-kotlin/actions)
[![Commit](https://img.shields.io/github/last-commit/Cessup/alebrije-multiplatform-kotlin
)](https://github.com/Cessup/alebrije-multiplatform-kotlin)



This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM). It is an application to make some module to a ecommerce but that contains some general features.

> The project is also available [with native UI written in Jetpack Compose and SwiftUI](https://github.com/Cessup/alebrije-multiplatform-kotlin).
>
> The [`develop` branch](https://github.com/Cessup/alebrije-multiplatform-kotlin) showcase a stable version.


## Modules Project

* [/composeApp](composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
      Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

* [/iosApp](iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](shared/src) is for the code that will be shared between all targets in the project.
  The most important subfolder is [commonMain](shared/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

## Technologies

The data displayed by the alebrije app is from [Chingon API](https://github.com/Cessup/chingon-general-api-ktor/).

The app uses the following multiplatform dependencies in its implementation:

- [Compose Multiplatform](https://jb.gg/compose) for UI
- [Ktor](https://ktor.io/) for networking
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON handling
- [Kamel](https://github.com/Kamel-Media/Kamel) for image loading
- [Kotlin-inject](https://github.com/evant/kotlin-inject?tab=readme-ov-file) for dependency injection

> [!NOTE]
> `Koin` doesn't support wasm that is the reason for use `kotlin-inject`.

> [!IMPORTANT]
> The libraries are going to update when any project will absolute but before data we'll notify you. But you are free to use anything libraries in this project because that is just a example.

### Adding the compiler dependencies

When configuring KSP for use with kotlin-inject, you can choose to generate the code into:

1. The common source set ([see below](#ksp-common-source-set-configuration) for the implementation of `configureCommonMainKsp`)
2. Each individual KMP target source set

You can see it in a top level `dependencies` block

> [!TIP]
> There is a good chance that the API for adding KSP compiler dependencies will change in the future.

```kotlin
dependencies {
    // 1. Configure code generation into the common source set
    kspCommonMainMetadata(libs.kotlinInject.compiler)

    // 2. Configure code generation into each KMP target source set
    add("kspAndroid", libs.kotlinInject.compiler)
    add("kspIosX64", libs.kotlinInject.compiler)
    add("kspIosArm64", libs.kotlinInject.compiler)
    add("kspIosSimulatorArm64", libs.kotlinInject.compiler)
    // add more targets here...
}

tasks.matching { it.name.startsWith("ksp") && it.name != "kspCommonMainKotlinMetadata" }.configureEach {
    dependsOn("kspCommonMainKotlinMetadata")
}
```

## Build and Run

### :green_circle: Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### :desktop_computer: Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### :earth_americas: Web Application

To build and run the development version of the web app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
  ```

### :iphone: iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](iosApp) directory in Xcode and run it from there.

> [!WARNING]
> Directly running or emulating native iOS applications on a Windows machine within IntelliJ IDEA is not possible. This is due to Apple's ecosystem requirements, which mandate the use of macOS for building and running iOS applications and emulators.

---

### About Kotlin Multiplatform
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…


