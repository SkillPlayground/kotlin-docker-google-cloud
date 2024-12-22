plugins { //
    application
    alias(libs.plugins.kotlin.jvm)
}

application { //
    mainClass = "com.javiersc.kotlin.docker.MainKt"
}

dependencies { //
    implementation(libs.postgresql)
}
