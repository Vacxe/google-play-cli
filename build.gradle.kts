import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("idea")
    id("org.jetbrains.kotlin.jvm")
    id("jacoco")
    kotlin("plugin.serialization") version "1.7.21"
}

application {
    mainClassName = "com.github.vacxe.googleplaycli.PlayStoreCliKt"
    applicationName = "google-play-cli"
}

repositories {
    jcenter()
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation(Libraries.clikt)
    implementation(Libraries.googleOAuth)
    implementation(Libraries.googleAppPublisher)
    implementation(Libraries.serializationJson)

    testImplementation(TestLibraries.junit)
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
    }
}
