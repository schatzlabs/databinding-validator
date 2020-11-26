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

package com.schatzlabs.validator

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.schatzlabs.validator.helpers.ValidationMode
import com.schatzlabs.validator.helpers.ViewTagHelper
import com.schatzlabs.validator.listeners.ValidateListener
import com.schatzlabs.validator.rules.Rule

/**
 * @author Zakayo Thuku
 * */

/**
 * Validator class for handling Input validation
 * @param binding [ViewDataBinding]
 **/
class Validator(private val binding: ViewDataBinding) {

    private val disabledViews: MutableSet<View>
    private var validationMode: @ValidationMode Int = ValidationMode.FIELD_VALIDATION
    private var validateListener: ValidateListener? = null

    init {
        disabledViews = HashSet()
    }

    /**
     * Function enables binding validation on a view
     * @param view
     **/
    fun enableValidation(view: View) {
        disabledViews.remove(view)
    }

    /**
     * Function disables binding validation on a view
     * @param view
     **/
    fun disableValidation(view: View) {
        disabledViews.add(view)
    }

    /**
     * Function enables form validation mode
     **/
    fun setFormValidationMode() {
        validationMode = ValidationMode.FORM_VALIDATION
    }

    /**
     * Function enables field validation mode
     **/
    fun setFieldValidationMode() {
        validationMode = ValidationMode.FIELD_VALIDATION
    }

    /**
     * Function to apply validation the views in form mode
     **/
    fun validate(): Boolean {
        return isViewsValid(getViewsWithValidation())
    }

    /**
     * Function to apply validation on a view
     * @param view
     **/
    fun validate(view: View): Boolean {
        return isViewsValid(getViewsWithValidation(view))
    }

    /**
     * Function to disable binding validation on a list of views passed
     * @param views
     **/
    fun <ViewType : View> validate(views: List<ViewType>): Boolean {
        return isViewsValid(getViewsWithValidation(views))
    }

    /**
     * Function checks if the views passed are valid
     * @param views
     **/
    private fun isViewsValid(views: List<View>): Boolean {
        var viewsValid = true
        for (view in views) {
            var viewValid = true
            val rules = view.getTag(R.id.validator_rule) as List<*>
            for (rule in rules) {
                viewValid = viewsValid && isRuleValid(rule as Rule<*, *>)
                viewsValid = viewsValid && viewValid
            }

            if (validationMode == ValidationMode.FIELD_VALIDATION && !viewValid) break
        }
        return viewsValid
    }

    /**
     * Function to checks if the rule applied on a view is valid
     * @param rule
     **/
    private fun isRuleValid(rule: Rule<*, *>): Boolean {
        return disabledViews.contains(rule.view) || rule.validate()
    }

    /**
     * Function returns all views which have binding validation set-up
     **/
    private fun getViewsWithValidation(): List<View> {
        return when (binding.root) {
            is ViewGroup -> {
                ViewTagHelper.getViewsByTag(binding.root as ViewGroup, R.id.validator_rule)
            }
            else -> {
                listOf(binding.root)
            }
        }
    }

    /**
     * Function filters all views which have binding validation set-up
     * @param view
     **/
    private fun getViewsWithValidation(view: View): List<View> {
        return ViewTagHelper.filterViewWithTag(R.id.validator_rule, view)
    }

    /**
     * Function filters all views which have binding validation set-up
     * @param views
     **/
    private fun <ViewType : View> getViewsWithValidation(views: List<ViewType>): List<View> {
        return ViewTagHelper.filterViewsWithTag(R.id.validator_rule, views)
    }

    /**
     * Function initializes the [ValidateListener]
     * @param validateListener
     * @see ValidateListener
     **/
    fun initValidateListener(validateListener: ValidateListener) {
        this.validateListener = validateListener
    }

    /**
     * Function initializes the [ValidateListener] callbacks
     * @see ValidateListener
     **/
    fun initCallbacks() {
        if (validateListener == null) {
            throw IllegalArgumentException("Validation listener should not be null.")
        }
        if (validate()) {
            validateListener!!.onValidationSuccess()
        } else {
            validateListener!!.onValidationFailure()
        }
    }
}
