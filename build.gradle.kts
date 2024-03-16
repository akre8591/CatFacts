// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    jacoco
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
