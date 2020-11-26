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

import android.view.View

/**
 * @author Zakayo Thuku
 * */

/**
 * Abstract class for applying and validating
 * the value of type [ValueType] of a View of
 * type [ViewType] against a Rule.
 */
abstract class Rule<ViewType : View, ValueType>(
    view: ViewType,
    protected var value: ValueType,
    protected var errorMessage: String
) {
    var view: ViewType
        protected set

    init {
        this.view = view
    }

    fun validate(): Boolean {
        val valid = isValid(view)
        if (valid) {
            onValidationSuccess(view)
        } else {
            onValidationFailure(view)
        }
        return valid
    }

    protected abstract fun isValid(view: ViewType): Boolean

    protected abstract fun onValidationSuccess(view: ViewType)

    protected abstract fun onValidationFailure(view: ViewType)
}
