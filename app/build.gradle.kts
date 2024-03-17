import java.util.Locale

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    jacoco
}

jacoco.toolVersion = "0.8.11"

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}


android {

    applicationVariants.all {
        val testTaskName = "test${
            this.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.US
                ) else it.toString()
            }
        }UnitTest"

        val fileFilter = mutableSetOf(
            "**/databinding/*Binding.*",
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*",
            "dagger/hilt/internal/aggregatedroot/codegen/*.*",
            // butterKnife
            "**/*\$ViewInjector*.*",
            "**/*\$ViewBinder*.*",
            "**/Lambda$*.class",
            "**/Lambda.class",
            "**/*Lambda.class",
            "**/*Lambda*.class",
            "**/*_MembersInjector.class",
            "**/Dagger*Component*.*",
            "**/*Module_*Factory.class",
            "**/di/module/*",
            "**/*_Factory*.*",
            "**/*Module*.*",
            "**/*Dagger*.*",
            "**/*Hilt*.*",
            // kotlin
            "**/*MapperImpl*.*",
            "**/*\$ViewInjector*.*",
            "**/*\$ViewBinder*.*",
            "**/BuildConfig.*",
            "**/*Component*.*",
            "**/*BR*.*",
            "**/Manifest*.*",
            "**/*\$Lambda$*.*",
            "**/*Companion*.*",
            "**/*Module*.*",
            "**/*Dagger*.*",
            "**/*Hilt*.*",
            "**/*MembersInjector*.*",
            "**/*_MembersInjector.class",
            "**/*_Factory*.*",
            "**/*_Impl*.*",
            "**/*_Bind*.*",
            "**/*_GeneratedInjector*.*",
            "**/*_Provide*Factory*.*",
            "**/*Extensions*.*",
            "**/*Fake*.*",
            "**/*Preview*.*",
            "**/*\$Lambda$*.*", // Jacoco can not handle several "$" in class name.
            "**/*\$inlined$*.*" // Kotlin specific, Jacoco can not handle several "$" in class name.
        )

        val kotlinClassesPath: Provider<Directory> =
            layout.buildDirectory.dir("tmp/kotlin-classes/${this.name}")

        val classDirectoriesTree = files(
            fileTree(javaCompileProvider.get().destinationDirectory) {
                exclude(fileFilter)
            },
            fileTree(kotlinClassesPath.get().asFile) {
                exclude(fileFilter)
            }
        )

        val sourceDirectoriesTree = fileTree("${layout.buildDirectory}") {
            include(
                "src/main/java/**",
                "src/main/kotlin/**",
                "src/debug/java/**",
                "src/debug/kotlin/**"
            )
        }

        val executionDataPath: Provider<Directory> =
            layout.buildDirectory.dir("jacoco/$testTaskName.exec")
        val executionDataTree = fileTree(executionDataPath)

        fun JacocoReportsContainer.reports() {
            xml.required.set(true)
            html.required.set(true)
            xml.outputLocation =
                layout.buildDirectory.file("reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
            html.outputLocation = layout.buildDirectory.dir("reports/jacoco/jacocoTestReport/html")
        }

        fun JacocoCoverageVerification.setDirectories() {
            sourceDirectories.setFrom(sourceDirectoriesTree)
            classDirectories.setFrom(classDirectoriesTree)
            executionData.setFrom(executionDataTree)
        }

        fun JacocoReport.setDirectories() {
            sourceDirectories.setFrom(sourceDirectoriesTree)
            classDirectories.setFrom(classDirectoriesTree)
            executionData.setFrom(executionDataTree)
        }

        val reportTask = "jacoco${
            testTaskName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }Report"

        if (tasks.findByName(reportTask) == null) {
            tasks.register(reportTask, JacocoReport::class) {
                group = "Reporting"
                description = "Code coverage report for both Android and Unit tests."
                dependsOn("testDebugUnitTest")
                reports {
                    reports()
                }
                setDirectories()
            }
        }

        if (tasks.findByName(reportTask) == null) {
            tasks.register<JacocoCoverageVerification>(reportTask) {
                group = "Reporting"
                description = "Code coverage verification for Android both Android and Unit tests."
                dependsOn("testDebugUnitTest")
                violationRules {
                    rule {
                        limit {
                            counter = "INSTRUCTIONAL"
                            value = "COVEREDRATIO"
                            minimum = "0.5".toBigDecimal()
                        }
                    }
                }
                setDirectories()
            }
        }
    }

    namespace = "com.example.technicaltest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.technicaltest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            enableAndroidTestCoverage = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            unitTests.all {
                testCoverage.jacocoVersion = "0.8.11"
            }
        }
    }

    ktlint {
        filter {
            exclude("**/generated/**")
            exclude("build.gradle.kts")
            include("**/kotlin/**")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.google.dagger:hilt-android:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-android-compiler:2.51")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.github.skydoves:sandwich:1.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("androidx.compose.ui:ui-test-junit4:1.6.3")
    testImplementation("app.cash.turbine:turbine:1.1.0")
    testImplementation("org.robolectric:robolectric:4.11.1")
}
