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
import com.schatzlabs.validator.rules.RegexRule

@BindingAdapter(
    value = ["validateRegex", "validateRegexMessage", "validateRegexAutoDismiss"],
    requireAll = false
)
fun bindingRegex(view: TextView, pattern: String, errorMessage: String, autoDismiss: Boolean) {

    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
        view = view,
        errorMessage = errorMessage,
        defaultMessage = R.string.error_message_regex_validation
    )

    ViewTagHelper.appendValue(
        tagId = R.id.validator_rule,
        view = view,
        value = RegexRule(view, pattern, handledErrorMessage)
    )
}
