plugins {
  kotlin("jvm") version "2.0.0-RC3"
  kotlin("plugin.serialization") version "2.0.0-RC3"

  id("org.jetbrains.dokka") version "1.9.20"

  `java-library`
  `maven-publish`
}

val groupId = "io.github.roughlyunderscore"
val artifactId = "ULib"
val version = "1.7"

group = groupId
project.version = version

java {
  // withJavadocJar()
  withSourcesJar()
}

repositories {
  mavenCentral()
  maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      this.groupId = this@Build_gradle.groupId
      this.artifactId = this@Build_gradle.artifactId
      this.version = this@Build_gradle.version

      from(components["java"])
    }
  }
}

/*
tasks.javadoc {
  if (JavaVersion.current().isJava9Compatible) {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
  }
}
*/

tasks.register<Jar>("dokkaHtmlJar") {
  dependsOn(tasks.dokkaHtml)
  from(tasks.dokkaHtml.flatMap { it.outputDirectory })
  archiveClassifier.set("html-docs")
}

tasks.register<Jar>("dokkaJavadocJar") {
  dependsOn(tasks.dokkaJavadoc)
  from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
  archiveClassifier.set("javadoc")
}

tasks.build {
  dependsOn("dokkaHtmlJar", "dokkaJavadocJar", tasks.publishToMavenLocal)
}

dependencies {
  implementation("com.google.code.gson:gson:2.10.1")

  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0-RC")

  implementation("org.apache.commons:commons-compress:1.26.1")

  compileOnly("org.spigotmc:spigot-api:1.20.6-R0.1-SNAPSHOT")
  compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
}

kotlin {
  jvmToolchain(8)
}