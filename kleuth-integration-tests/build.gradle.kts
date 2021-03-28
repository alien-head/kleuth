import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.spring")

  id("org.jlleitschuh.gradle.ktlint")

  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

dependencies {
  implementation(project(":kleuth"))
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("org.apache.commons:commons-csv:1.8")

  testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
  testImplementation("io.kotest:kotest-extensions-spring:4.3.2")
  testImplementation("io.kotest:kotest-assertions-core:4.3.2")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

group = "io.alienhead"
version = "0.4.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
  mavenCentral()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
