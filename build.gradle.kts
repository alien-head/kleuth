plugins {
  kotlin("jvm") version "1.4.21"
  kotlin("plugin.spring") version "1.4.21"
  id("org.springframework.boot") version "2.4.3" apply false
  id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

repositories {
  mavenCentral()
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
  disabledRules.set(setOf("final-newline"))
}

tasks.build {
  dependsOn("ktlintFormat")
}
