plugins {
    id("java")
}

group = "dev.xoapp.portals"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.powernukkitx.org/releases")
    maven("https://repo.powernukkitx.org/releases")
    maven("https://repo.powernukkitx.org/snapshots")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.powernukkitx:server:3.0.6-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}