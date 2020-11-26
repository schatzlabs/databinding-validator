/*
 * Copyright 2020 Schatz Designs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.schatzlabs.validator.helpers

import androidx.annotation.IntDef
import com.schatzlabs.validator.helpers.ValidationMode.Companion.FIELD_VALIDATION
import com.schatzlabs.validator.helpers.ValidationMode.Companion.FORM_VALIDATION

/**
 * @author Zakayo Thuku
 * */

/**
 * Validation mode of a view supplied to the Validator.
 */
@Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    FORM_VALIDATION,
    FIELD_VALIDATION
)
annotation class ValidationMode {
    companion object {
        const val FORM_VALIDATION = 1
        const val FIELD_VALIDATION = 0
    }
}
