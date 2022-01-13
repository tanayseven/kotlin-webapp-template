package com.tanayseven.simple_web_app

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.matchers.ints.shouldBeExactly

// does property-based testing
class StringsOperationsTest : StringSpec({
    "String concatenation" {
        forAll<String, String> { a, b ->
            concat(a, b).length shouldBeExactly a.length + b.length
        }
    }
})
