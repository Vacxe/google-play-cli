object Versions {
    const val clikt = "2.6.0"
    const val googleOAuth = "1.13.0"
    const val googleAppPublisher = "v3-rev20240418-2.0.0"
    const val junit = "4.13"
}

object Libraries {
    const val clikt = "com.github.ajalt:clikt:${Versions.clikt}"
    const val googleOAuth = "com.google.auth:google-auth-library-oauth2-http:${Versions.googleOAuth}"
    const val googleAppPublisher = "com.google.apis:google-api-services-androidpublisher:${Versions.googleAppPublisher}"
    const val serializationJson = ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
}
