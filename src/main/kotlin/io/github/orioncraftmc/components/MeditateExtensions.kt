/*
 * MIT License
 *
 * Copyright (c) 2021 OrionCraftMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.orioncraftmc.components

import io.github.orioncraftmc.meditate.YogaNode
import io.github.orioncraftmc.components.model.Size

/**
 * Calls the [YogaNode.setWidth] and [YogaNode.setHeight] of the [YogaNode] to the given [Size].
 */
var YogaNode.nodeSize: Size
	get() {
		return Size(width.value.toDouble(), height.value.toDouble())
	}
	set(value) {
		setWidth(value.width.toFloat())
		setHeight(value.height.toFloat())
	}
