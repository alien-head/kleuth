import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("io.spring.dependency-management")
  `maven-publish`

  id("org.jlleitschuh.gradle.ktlint")
}

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.springframework.boot:spring-boot-autoconfigure-processor")
  implementation("org.springframework.boot:spring-boot-starter-web")

  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("net.pearx.kasechange:kasechange:1.3.0")

  testImplementation("io.kotest:kotest-runner-junit5:4.3.2")

  testImplementation("io.kotest:kotest-assertions-core:4.3.2")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

group = "io.alienhead"
version = "0.0.2-SNAPSHOT"

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

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      artifactId = "kleuth"
      from(components["java"])
    }
  }
}
