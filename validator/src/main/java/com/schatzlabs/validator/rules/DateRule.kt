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

package com.schatzlabs.validator.rules

import android.widget.TextView
import com.schatzlabs.validator.helpers.EditTextHelper
import org.apache.commons.validator.routines.DateValidator

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper class for applying and validating Date against standard rules
 * @param view
 * @param value
 * @param errorMessage
 *
 * @see DateValidator
 * @see Rule
 */
class DateRule(
    view: TextView,
    value: String,
    errorMessage: String
) : Rule<TextView, String>(view, value, errorMessage) {

    private val dateValidator: DateValidator = DateValidator()

    override fun isValid(view: TextView): Boolean {
        return dateValidator.isValid(view.text.toString(), value)
    }

    override fun onValidationSuccess(view: TextView) {
        EditTextHelper.removeError(view)
    }

    override fun onValidationFailure(view: TextView) {
        EditTextHelper.setError(view, errorMessage)
    }
}
