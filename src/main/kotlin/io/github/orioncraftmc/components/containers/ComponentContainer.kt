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

package io.github.orioncraftmc.components.containers

import com.github.ajalt.colormath.Color
import io.github.orioncraftmc.components.Component
import io.github.orioncraftmc.components.OrionComponents.bridge
import io.github.orioncraftmc.components.model.Anchor
import io.github.orioncraftmc.components.model.Padding
import io.github.orioncraftmc.components.model.Point
import io.github.orioncraftmc.components.model.Size
import io.github.orioncraftmc.components.utils.ComponentUtils
import io.github.orioncraftmc.components.utils.ComponentUtils.computeMousePosition
import io.github.orioncraftmc.components.utils.ComponentUtils.isMouseWithinComponent
import io.github.orioncraftmc.meditate.YogaNode
import java.util.*

/**
 * A component that contains other components and renders them to the screen.
 */
open class ComponentContainer : Component {
	val components: List<Component> by lazy { Collections.unmodifiableList(componentsList) }
	protected val componentsList = mutableListOf<Component>()

	override var anchor: Anchor = Anchor.TOP_LEFT
	final override var padding: Padding = Padding(0.0)
	override var position: Point = Point()
	override var size: Size = Size()
	override var parent: Component? = null
	override var backgroundColor: Color? = null
	override var scale: Double = 1.0
	final override var snapToDevicePixels: Boolean = false
	override var flexLayoutNode: YogaNode? = null

	fun addComponent(component: Component) {
		component.parent = this
		componentsList.add(component)

		val childFlexLayoutNode = component.flexLayoutNode ?: return

		flexLayoutNode?.apply {
			addChildAt(childFlexLayoutNode, childCount)
		}
	}

	override fun renderComponent(mouseX: Int, mouseY: Int) {
		ComponentUtils.performRootComponentLayout(this)
		componentsList.forEach {
			bridge.pushPopMatrix {
				performComponentLayout(it)
				ComponentUtils.renderComponent(it, mouseX, mouseY, false)
			}
		}
	}

	override fun handleMouseClick(mouseX: Int, mouseY: Int) {
		doActionIfMouseIsOverComponent(mouseX, mouseY) { finalMouseX, finalMouseY ->
			handleMouseClick(finalMouseX, finalMouseY)
		}
	}

	override fun handleMouseRelease(mouseX: Int, mouseY: Int) {
		doActionIfMouseIsOverComponent(mouseX, mouseY) { finalMouseX, finalMouseY ->
			handleMouseRelease(finalMouseX, finalMouseY)
		}
	}

	private inline fun doActionIfMouseIsOverComponent(
		mouseX: Int,
		mouseY: Int,
		action: Component.(finalMouseX: Int, finalMouseY: Int) -> Unit
	) {
		componentsList.forEach { component ->
			val (finalMouseX, finalMouseY) = computeMousePosition(component, mouseX, mouseY)

			val isMouseWithinComponent = isMouseWithinComponent(finalMouseX, finalMouseY, component)
			if (isMouseWithinComponent) {
				action(component, finalMouseX, finalMouseY)
			}
		}
	}

	open fun performComponentLayout(component: Component) {
		ComponentUtils.performComponentLayout(component)
	}
}


