package ajman.shayan.joisty.models.shapes

import kotlinx.serialization.Serializable

@Serializable
data class CanvasDataModel(
    val rectangles: List<Rectangle>,
    val circles: List<Circle>,
    val paths: List<List<Point>>
) {
    constructor(rectangle: Rectangle) : this(listOf(rectangle))
    constructor(elements: List<Any>) : this(
        elements.filterIsInstance<CanvasDataModel>().flatMap { it.rectangles }
                + elements.filterIsInstance<Rectangle>(),
        elements.filterIsInstance<CanvasDataModel>().flatMap { it.circles }
                + elements.filterIsInstance<Circle>(),
        elements.filterIsInstance<CanvasDataModel>().flatMap { it.paths }
                + elements.filterIsInstance<List<Point>>()
                + listOf(elements.filterIsInstance<Point>())
    )

    fun mirrorY(): CanvasDataModel {
        val resizedRectangles = rectangles.map { rectangle ->
            Rectangle(rectangle.x, -rectangle.y, rectangle.w, -rectangle.h)
        }
        val resizedCircles = circles.map { circle ->
            Circle(circle.x, -circle.y, circle.r)
        }
        val resizedPaths = paths.map { path ->
            path.map { point ->
                Point(point.x, -point.y)
            }
        }
        return CanvasDataModel(resizedRectangles, resizedCircles, resizedPaths)
    }

    fun resizeBy(k: Double): CanvasDataModel {
        val resizedRectangles = rectangles.map { rectangle ->
            Rectangle(k * rectangle.x, k * rectangle.y, k * rectangle.w, k * rectangle.h)
        }
        val resizedCircles = circles.map { circle ->
            Circle(k * circle.x, k * circle.y, k * circle.r)
        }
        val resizedPaths = paths.map { path ->
            path.map { point ->
                Point(k * point.x, k * point.y)
            }
        }
        return CanvasDataModel(resizedRectangles, resizedCircles, resizedPaths)
    }

    fun moveTo(x: Double, y: Double): CanvasDataModel {
        val movedRectangles = rectangles.map { rectangle ->
            Rectangle(rectangle.x + x, rectangle.y + y, rectangle.w, rectangle.h)
        }
        val movedCircles = circles.map { circle ->
            Circle(circle.x + x, circle.y + y, circle.r)
        }
        val movedPaths = paths.map { path ->
            path.map { point ->
                Point(point.x + x, point.y + y)
            }
        }
        return CanvasDataModel(movedRectangles, movedCircles, movedPaths)
    }

//    fun combineWith(model: CanvasDataModel): CanvasDataModel {
//        val combinedRectangles = rectangles + model.rectangles
//        val combinedCircles = circles + model.circles
//        val combinedPaths = paths + model.paths
//        return CanvasDataModel(combinedRectangles, combinedCircles, combinedPaths)
//    }
}

@Serializable
data class Rectangle(val x: Double, val y: Double, val w: Double, val h: Double)

@Serializable
data class Circle(val x: Double, val y: Double, val r: Double)

@Serializable
data class Point(val x: Double, val y: Double)
