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

package io.github.orioncraftmc.components.model

import io.github.orioncraftmc.components.utils.AnchorUtils

enum class Anchor {
	TOP_LEFT,
	TOP_MIDDLE,
	TOP_RIGHT,

	MIDDLE_LEFT,
	MIDDLE,
	MIDDLE_RIGHT,

	BOTTOM_LEFT,
	BOTTOM_MIDDLE,
	BOTTOM_RIGHT;

	fun getSideAtTop(): Anchor {
		val (left, _, right) = AnchorUtils.extractXInformationFromAnchor(this)
		return if (left) {
			TOP_LEFT
		} else if (right) {
			TOP_RIGHT
		} else {
			TOP_MIDDLE
		}
	}
}
