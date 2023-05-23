plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("com.diffplug.spotless") version "6.18.0"
}

group = "me.rzblue"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("dev.kord:kord-core:0.9.0")
    implementation("com.sksamuel.hoplite:hoplite-core:2.7.4")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.4")
    implementation("org.tinylog:tinylog-api:2.6.2")
    implementation("org.tinylog:tinylog-impl:2.6.2")
    implementation("org.tinylog:slf4j-tinylog:2.6.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

spotless {
    kotlin {
        ktfmt()
    }
}