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

import android.text.TextUtils
import android.widget.TextView
import com.schatzlabs.validator.helpers.EditTextHelper

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper class for validating empty input values
 * @param view
 * @param value
 * @param errorMessage
 *
 * @see Rule
 */
class EmptyRule(
    view: TextView,
    value: Boolean,
    errorMessage: String
) : Rule<TextView, Boolean>(view, value, errorMessage) {

    override fun isValid(view: TextView): Boolean {
        return !value || !TextUtils.isEmpty(view.text)
    }

    override fun onValidationSuccess(view: TextView) {
        EditTextHelper.removeError(view)
    }

    override fun onValidationFailure(view: TextView) {
        EditTextHelper.setError(view, errorMessage)
    }
}
