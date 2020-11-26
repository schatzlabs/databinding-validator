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

package com.schatzlabs.validator.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.schatzlabs.validator.R
import com.schatzlabs.validator.helpers.EditTextHelper
import com.schatzlabs.validator.helpers.ErrorMessageHelper
import com.schatzlabs.validator.helpers.ViewTagHelper
import com.schatzlabs.validator.rules.MinNumberRule
import com.schatzlabs.validator.rules.MaxNumberRule

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper function that creates a binding adapter to validate an input is greater than the value passed
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateMinNumber", "validateNumberMessage", "validateNumberAutoDismiss"],
    requireAll = false
)
fun bindingMinNumber(view: TextView, minNumber: Int, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_number_less_than,
        value = minNumber
    )

    ViewTagHelper.appendValue(
        tagId = R.id.validator_rule,
        view = view,
        value = MinNumberRule(view, minNumber, handledErrorMessage)
    )
}

/**
 * Helper function that creates a binding adapter to validate an input is less than the value passed
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateMaxNumber", "validateNumberMessage", "validateNumberAutoDismiss"],
    requireAll = false
)
fun bindingMaxNumber(view: TextView, maxNumber: Int, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_number_greater_than,
        value = maxNumber
    )

    ViewTagHelper.appendValue(
        tagId = R.id.validator_rule,
        view = view,
        value = MaxNumberRule(view, maxNumber, handledErrorMessage)
    )
}
