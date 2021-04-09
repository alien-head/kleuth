package io.alienhead.kleuth.utils

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class KebabCaseTests : DescribeSpec({
  describe("given string with pascal case") {
    var toConvert: String

    describe("and no pathVariables") {
      toConvert = "thisIsATest"

      it("should convert to kebab case") {
        toConvert.kebabCase() shouldBe "this-is-a-test"
      }
    }

    describe("with pathVariables") {
      toConvert = "thisIs{aTest}"

      it("should ignore path variables but convert all other uppercases") {
        toConvert.kebabCase() shouldBe "this-is{aTest}"
      }
    }

    describe("when the path is complex") {
      toConvert = "this/iSa{testId}/{anotherTestId}/again/anotherOne"

      it("should convert correctly") {
        toConvert.kebabCase() shouldBe "this/i-sa{testId}/{anotherTestId}/again/another-one"
      }
    }
  }
})
