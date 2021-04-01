import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `maven-publish`
  `java-library`
  signing
  kotlin("jvm")
  kotlin("plugin.spring")
  id("io.spring.dependency-management")
  id("org.jlleitschuh.gradle.ktlint")

  id("org.jetbrains.dokka") version ("1.4.30")
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
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")

  implementation("net.pearx.kasechange:kasechange:1.3.0")

  testImplementation("io.kotest:kotest-runner-junit5:4.3.2")

  testImplementation("io.kotest:kotest-assertions-core:4.3.2")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

group = "io.alienhead"
version = "0.9.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/kotlinx/")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

tasks.create("dokkaJar", Jar::class.java) {
  this.group = JavaBasePlugin.DOCUMENTATION_GROUP
  this.description = "Javadoc with Dokka"
  this.archiveClassifier.set("javadoc")
  this.from(tasks.getAt("dokkaJavadoc"))
}

java {
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      groupId = "io.alienhead"
      artifactId = "kleuth"
      version = "0.9.0"

      from(components["java"])
      artifact(tasks.getAt("dokkaJar"))

      pom {
        name.set("Kleuth")
        description.set("A framework for dynamically creating Spring Rest Controllers.")
        url.set("https://github.com/alien-head/kleuth")
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            id.set("cjpoulsen")
            name.set("Christopher Poulsen")
            email.set("jlpoulsen12@gmail.com")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/alien-head/kleuth.git")
          developerConnection.set("scm:git:ssh://github.com:alien-head/kleuth.git")
          url.set("https://github.com/alien-head/kleuth/tree/master")
        }
      }
    }
  }
}

signing {
  setRequired({
    gradle.taskGraph.hasTask("publish")
  })

  val signingKeyId: String? by project
  val signingKey: String? by project
  val signingPassword: String? by project
  useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
  sign(publishing.publications["mavenJava"])
}
