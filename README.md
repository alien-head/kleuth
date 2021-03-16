# Kleuth

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

kotlin + sleuth = kleuth 🕵️‍♂️ . A lightweight framework for generating Spring REST API routes through a directory structure. 

Many developers using Spring try to use as few controllers as possible: shoving functions for multiple request methods and branching routes into a single file. This obfuscates the structure of the REST API and requires someone to dig through the code to figure out what is going on. Kleuth helps ensure the organization of the source code describes the layout of the REST API, thus enforcing REST API conventions while also removing a good chunk of Spring boilerplate and preventing controllers from ballooning in scope. This makes the code concise and clear for anyone to read.

## Features

## Kleuth Conventions

### Other allowed behaviors

Although it goes against the idea of one handler function per route handler class, handler classes can have any number of handler functions so long as every additional handler function is annotated with the appropriate `RequestMethod` (`Get`, `Post`, `Put`, etc.). 

> Tip! This is also useful for 1-to-1 route handlers if you want your handler function to have a unique name. The `RequestMethod` annotation supercedes the class name when defining a route.

## Setup

## Compatibility Notes
Handler functions work exactly like Spring `@RequestMapping` functions. However,
function-level Spring annotations do not work on handler functions (`@ResponseStatus`, `@PreAuthorize`). Luckily, Spring commonly provides multiple ways to get the same job done. 
> Tip! Ant matchers can handle the same behavior of `@PreAuthorize`, for one example. If you are following the Kleuth conventions and your route handlers are 1-to-1 with route handler classes, then this is the correct way to handle preauthorization anyway.

Since handler classes retain the `@RestController` annotation, `@RequestMapping` functions can even appear inside handler classes.

Rolling back from Kleuth is easy enough--just remove the Kleuth annotations and add `@RequestMapping` annotations to the route handler function(s).

## Developer Setup

#### Ktlint
Kleuth uses the [JLLeitschuh Ktlint Gradle plugin](https://github.com/JLLeitschuh/ktlint-gradle) to manage Kotlin code linting. 
The following setup is required when working with the project:
 - Setup the kotlin code style by running `./gradlew ktlintApplyToIdea` to apply it on the Kleuth project, 
   or `./graldew ktlintApplyToIdeaGlobally` to apply it for every project.
 - Add the ktlint pre-commit hook with `./gradlew addKtlintCheckGitPreCommitHook` 
   to ensure your PR verify will not fail because of linting errors.
