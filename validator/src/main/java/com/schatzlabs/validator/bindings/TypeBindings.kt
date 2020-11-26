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
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.schatzlabs.validator.R
import com.schatzlabs.validator.helpers.EditTextHelper
import com.schatzlabs.validator.helpers.ErrorMessageHelper
import com.schatzlabs.validator.helpers.ViewTagHelper
import com.schatzlabs.validator.rules.TypeRule

/**
 * @author Zakayo Thuku
 * */

/**
 * This helper function creates a binding adapter for validating an input type
 * @see TypeRule
 * @see BindingAdapter
 */
@BindingAdapter(
    value = ["validateType", "validateTypeMessage", "validateTypeAutoDismiss"],
    requireAll = false
)
fun bindingTypeValidation(view: TextView, fieldTypeText: String?, errorMessage: String?, autoDismiss: Boolean) {
    if (autoDismiss) {
        EditTextHelper.disableErrorOnChanged(view)
    }

    val fieldType = getFieldTypeByText(fieldTypeText)

    try {

        val handledErrorMessage = ErrorMessageHelper.getStringOrDefault(
            view,
            errorMessage,
            fieldType.errorMessageId
        )
        ViewTagHelper.appendValue(R.id.validator_rule, view, fieldType.instantiate(view, handledErrorMessage))
    } catch (ignored: Exception) {
        ignored.printStackTrace()
    }
}

/**
 * Helper function to get the field type by text
 */
@NonNull
private fun getFieldTypeByText(fieldTypeText: String?): TypeRule.FieldType {
    var fieldType = TypeRule.FieldType.None
    for (type in TypeRule.FieldType.values()) {
        if (type.toString().equals(fieldTypeText, ignoreCase = true)) {
            fieldType = type
            break
        }
    }
    return fieldType
}
