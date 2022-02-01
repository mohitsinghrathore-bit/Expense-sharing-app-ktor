val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version = "3.1.5"
plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation ("mysql:mysql-connector-java:8.0.25")
    implementation ("org.ktorm:ktorm-core:3.4.1")
    implementation ("org.ktorm:ktorm-support-mysql:3.4.1")
    implementation("io.insert-koin:koin-ktor:3.1.5")
    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_version")
// SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_version")
}