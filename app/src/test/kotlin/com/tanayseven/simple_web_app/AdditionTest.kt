package com.tanayseven.simple_web_app

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeExactly

// example of doing parameterised tests
class AdditionTest : FunSpec({
    forAll(
        row(2, 4, 6),
        row(3, 9, 12),
        row(4, 16, 20),
        row(5, 25, 30)
    ) { num1, num2, sum ->
        test("Result of addition of $num1 + $num2 = $sum") {
            addition(num1, num2) shouldBeExactly sum
        }
    }
})
