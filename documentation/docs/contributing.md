---
title: Contributing
slug: /contributing
---

This page documents everything needed to contribute to Kleuth.

#### Ktlint
Kleuth uses the [JLLeitschuh Ktlint Gradle plugin](https://github.com/JLLeitschuh/ktlint-gradle) to manage Kotlin code linting.
The following setup is required when working with the project:
- Setup the kotlin code style by running `./gradlew ktlintApplyToIdea` to apply it on the Kleuth project,
  or `./graldew ktlintApplyToIdeaGlobally` to apply it for every project.
- Add the ktlint pre-commit hook with `./gradlew addKtlintCheckGitPreCommitHook`
  to ensure your PR verify will not fail because of linting errors.
