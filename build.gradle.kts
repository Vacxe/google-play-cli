import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("idea")
    id("org.jetbrains.kotlin.jvm")
    id("jacoco")
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
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(TestLibraries.junit)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
    }
}