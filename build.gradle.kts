plugins {
    id("java")
}

group = "dev.xoapp.portals"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.powernukkitx.org/releases")
    maven("https://projectlombok.org/edge-releases");

    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.opencollab.dev/maven-snapshots")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:edge-SNAPSHOT")
    annotationProcessor("org.projectlombok:lombok:edge-SNAPSHOT")

    implementation("org.powernukkitx:server:3.0.6-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(25)
}
