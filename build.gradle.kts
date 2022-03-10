import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.cadixdev.licenser") version "0.6.1"
    `maven-publish`
}

group = "io.github.orioncraftmc"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/NickAcPT/LightCraftMaven/main/")
}

dependencies {
    /* Colour Stuff */
    api("com.github.ajalt.colormath:colormath:3.2.0")

    /* Meditate Layout */
    api("io.github.orioncraftmc:meditate-layout:1.0.1")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

configure<org.cadixdev.gradle.licenser.LicenseExtension> {
    header(rootProject.file("LICENSE"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    repositories {
        val lightCraftRepoDir = project.findProperty("lightcraft.repo.location")
        if (lightCraftRepoDir != null) {
            maven {
                name = "LightCraftRepo"
                url = File(lightCraftRepoDir.toString()).toURI()
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
