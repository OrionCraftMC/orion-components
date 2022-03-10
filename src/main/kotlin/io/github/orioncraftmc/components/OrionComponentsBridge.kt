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

import com.github.ajalt.colormath.Color

interface OrionComponentsBridge {
	/**
	 * Enables open-gl blending.
	 */
	fun enableGlBlend()

	/**
	 * Translates the current matrix by the given x, y, and z values.
	 */
	fun translate(x: Double, y: Double, z: Double)

	/**
	 * Translates the current matrix by the given x, y, and z values.
	 */
	fun scale(x: Double, y: Double, z: Double)

	/**
	 * Fills the specified rectangle with the specified color.
	 */
	fun fillRectangle(x1: Double, y1: Double, width: Double, height: Double, color: Color)

	/**
	 * Performs [operation] by pushing and popping the current matrix.
	 *
	 * Pseudo implementation:
	 * ```kotlin
	 * // Push the current matrix
	 * pushMatrix()
	 * // Perform the operation
	 * operation()
	 * // Pop the current matrix
	 * popMatrix()
	 * ```
	 */
	fun pushPopMatrix(operation: () -> Unit)
}
