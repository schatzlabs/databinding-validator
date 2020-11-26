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
import com.schatzlabs.validator.rules.EmptyRule
import com.schatzlabs.validator.rules.MaxLengthRule
import com.schatzlabs.validator.rules.MinLengthRule

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper function that creates a binding adapter to validate an input against a min value passed
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateMinLength", "validateLengthMessage", "validateLengthAutoDismiss"],
    requireAll = false
)
fun bindingMinLength(view: TextView, minLength: Int, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_min_length,
        value = minLength
    )
    ViewTagHelper.appendValue(R.id.validator_rule, view, MinLengthRule(view, minLength, handledErrorMessage))
}

/**
 * Helper function that creates a binding adapter to validate an input against a max value passed
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateMaxLength", "validateLengthMessage", "validateLengthAutoDismiss"],
    requireAll = false
)
fun bindingMaxLength(view: TextView, maxLength: Int, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_max_length,
        value = maxLength
    )
    ViewTagHelper.appendValue(R.id.validator_rule, view, MaxLengthRule(view, maxLength, handledErrorMessage))
}

/**
 * Helper function that creates a binding adapter to validate an empty input
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateEmpty", "validateMessage", "validateAutoDismiss"],
    requireAll = false
)
fun bindingEmpty(view: TextView, empty: Boolean, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_empty_validation
    )
    ViewTagHelper.appendValue(R.id.validator_rule, view, EmptyRule(view, empty, handledErrorMessage))
}
