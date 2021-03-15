# kleuth

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

kotlin + sleuth = kleuth. A framework for generating Spring REST API routes through a directory structure.

### Developer Setup

#### Ktlint
Kleuth uses the [JLLeitschuh Ktlint Gradle plugin](https://github.com/JLLeitschuh/ktlint-gradle) to manage Kotlin code linting. 
The following setup is required when working with the project:
 - Setup the kotlin code style by running `./gradlew ktlintApplyToIdea` to apply it on the Kleuth project, 
   or `./graldew ktlintApplyToIdeaGlobally` to apply it for every project.
 - Add the ktlint pre-commit hook with `./gradlew addKtlintCheckGitPreCommitHook` 
   to ensure your PR verify will not fail because of linting errors.
