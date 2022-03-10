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

package io.github.orioncraftmc.components.containers.flow

import io.github.orioncraftmc.components.Component
import io.github.orioncraftmc.components.OrionComponents.bridge
import io.github.orioncraftmc.components.containers.ComponentContainer
import io.github.orioncraftmc.components.model.Size
import kotlin.math.ceil

open class FlowLayoutContainer(var direction: FlowLayoutDirection = FlowLayoutDirection.HORIZONTAL) :
	ComponentContainer() {
	override var size: Size
		get() = computeLayoutSize()
		set(value) {}

	override fun performComponentLayout(component: Component) {
		super.performComponentLayout(component)
		val previousComponents = componentsList.takeWhile { it != component }
		when (direction) {
			FlowLayoutDirection.HORIZONTAL -> {
				bridge.translate(previousComponents.sumOf { ceil(getComponentSize(it).width + 2) }, 0.0, 0.0)
			}
			FlowLayoutDirection.VERTICAL -> {
				bridge.translate(0.0, previousComponents.sumOf { getComponentSize(it).height + 1 }, 0.0)
			}
		}
	}

	private fun computeLayoutSize(): Size {
		return when (direction) {
			FlowLayoutDirection.HORIZONTAL -> {
				val width = components.sumOf { ceil(getComponentSize(it).width + 1) }
				val height = components.maxOf { getComponentSize(it).height }
				Size(width, height) + padding
			}
			FlowLayoutDirection.VERTICAL -> {
				val width = components.maxOf { getComponentSize(it).width }
				val height = components.sumOf { ceil(getComponentSize(it).height + 1) }
				Size(width, height) + padding
			}
		}
	}

	private fun getComponentSize(it: Component) = it.effectiveSize
}
