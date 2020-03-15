
plugins {
    application
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

application {
    mainClassName = "com.github.vacxe.apkpublisher"
    applicationName = "apkpublisher"
}

repositories {
    jcenter()
}

dependencies {
    implementation(Libraries.argParser)
    implementation(Libraries.googleApiClient)
    implementation(Libraries.googleOAuth)
    implementation(Libraries.googleAppPublisher)
    implementation(Libraries.axmlParser)
}
