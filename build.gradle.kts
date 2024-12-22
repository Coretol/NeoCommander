import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.21"
    id("org.jetbrains.dokka") version "2.0.0"
    id("io.papermc.paperweight.userdev") version "1.7.7"
    `maven-publish`
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("com.mojang:brigadier:1.0.18")
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }

    assemble {
        paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION
    }

    create<Copy>("buildPlugin") {
        from(jar)
        into("server/plugins")
    }

    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    val javadocJar by creating(Jar::class) {
        archiveClassifier.set("javadoc")
        from(javadoc, dokkaJavadoc)
    }
    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
    }
}