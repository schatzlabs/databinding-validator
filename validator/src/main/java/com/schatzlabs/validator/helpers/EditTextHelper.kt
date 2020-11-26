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

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.databinding.adapters.ListenerUtil
import com.google.android.material.textfield.TextInputLayout
import com.schatzlabs.validator.R

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper functions for EditText error handling
 */
object EditTextHelper {

    fun setError(textView: TextView, errorMessage: String?) {
        getTextInputLayout(textView).apply {
            if (this != null) {
                isErrorEnabled = !TextUtils.isEmpty(errorMessage)
                error = errorMessage
            } else {
                textView.error = errorMessage
            }
        }
    }

    fun removeError(textView: TextView) {
        setError(textView, null)
    }

    private fun getTextInputLayout(textView: TextView): TextInputLayout? {
        var textInputLayout: TextInputLayout? = null

        var parent = textView.parent
        while (parent is View) {
            if (parent is TextInputLayout) {
                textInputLayout = parent
                break
            }
            parent = parent.getParent()
        }
        return textInputLayout
    }

    fun disableErrorOnChanged(textView: TextView) {
        if (ListenerUtil.getListener<TextWatcher>(textView, R.id.text_watcher_clear_error) != null) {
            return
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setError(textView, null)
            }

            override fun afterTextChanged(s: Editable) {}
        }

        textView.addTextChangedListener(textWatcher)
        ListenerUtil.trackListener(textView, textView, R.id.text_watcher_clear_error)
    }
}
