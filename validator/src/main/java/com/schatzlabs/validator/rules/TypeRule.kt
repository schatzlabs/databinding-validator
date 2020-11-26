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
import androidx.annotation.StringRes
import com.schatzlabs.validator.R
import java.lang.reflect.InvocationTargetException

/**
 * @author Zakayo Thuku
 * */

/**
 * Abstract class for applying and validating
 * the value of type [FieldType] of a View of
 * type [TextView] against a Rule.
 *
 * @see Rule
 */
abstract class TypeRule(
    view: TextView,
    value: FieldType,
    errorMessage: String
) : Rule<TextView, TypeRule.FieldType>(view, value, errorMessage) {

    enum class FieldType {
        Email(EmailTypeRule::class.java, R.string.error_message_email_validation),
        Url(UrlTypeRule::class.java, R.string.error_message_url_validation),
        None;

        @StringRes
        var errorMessageId: Int = 0
        private lateinit var mClass: Class<out TypeRule>

        constructor(mClass: Class<out TypeRule>, @StringRes errorMessageId: Int) {
            this.mClass = mClass
            this.errorMessageId = errorMessageId
        }

        constructor()

        @Throws(
            NoSuchMethodException::class,
            IllegalAccessException::class,
            InvocationTargetException::class,
            InstantiationException::class
        )
        fun instantiate(view: TextView, errorMessage: String): TypeRule {
            if (this != None) {
                return mClass.getConstructor(TextView::class.java, String::class.java).newInstance(view, errorMessage)
            }
            throw IllegalStateException("It's not possible to bind a none value type")
        }
    }
}
