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
import android.view.ViewGroup

/**
 * @author Zakayo Thuku
 * */

/**
 * Helper functions for tag management of views
 */
object ViewTagHelper {

    fun <Type> appendValue(tagId: Int, view: View, value: Type) {
        val objectList = view.getTag(tagId)
        if (objectList is MutableList<*>) {
            (objectList as MutableList<Type>).add(value)
        } else {
            val typeList = ArrayList<Type>()
            typeList.add(value)
            view.setTag(tagId, typeList)
        }
    }

    fun getViewsByTag(root: ViewGroup, tagId: Int): List<View> {
        val views = ArrayList<View>()
        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child = root.getChildAt(i)
            if (child is ViewGroup) {
                views.addAll(getViewsByTag(child, tagId))
            }
            addViewWhenContainsTag(tagId, views, child)
        }
        return views
    }

    fun filterViewWithTag(tagId: Int, view: View): List<View> {
        val viewsWithTags = ArrayList<View>()
        addViewWhenContainsTag(tagId, viewsWithTags, view)
        return viewsWithTags
    }

    fun <ViewType : View> filterViewsWithTag(tagId: Int, views: List<ViewType>): List<View> {
        val viewsWithTags = ArrayList<View>()
        for (view in views) {
            addViewWhenContainsTag(tagId, viewsWithTags, view)
        }
        return viewsWithTags
    }

    private fun addViewWhenContainsTag(tagId: Int, views: MutableList<View>, view: View) {
        val tagValue = view.getTag(tagId)
        if (tagValue != null) {
            views.add(view)
        }
    }
}
