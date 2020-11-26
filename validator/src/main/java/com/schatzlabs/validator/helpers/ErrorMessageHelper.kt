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

import android.view.View
import androidx.annotation.StringRes

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper functions for returning input binding error messages
 */
object ErrorMessageHelper {

    fun getStringOrDefault(view: View, errorMessage: String?, @StringRes defaultMessage: Int): String {
        return errorMessage ?: view.context.getString(defaultMessage)
    }

    fun getStringOrDefault(
        view: View,
        errorMessage: String?,
        @StringRes defaultMessage: Int,
        value: Int
    ): String {
        return errorMessage ?: view.context.getString(defaultMessage, value)
    }

    fun getStringOrDefault(view: View, errorMessage: CharSequence?, @StringRes defaultMessage: Int): String {
        return errorMessage?.toString() ?: view.context.getString(defaultMessage)
    }

    fun getStringOrDefault(
        view: View,
        errorMessage: CharSequence?,
        @StringRes defaultMessage: Int,
        value: Int
    ): String {
        return errorMessage?.toString() ?: view.context.getString(defaultMessage, value)
    }
}
