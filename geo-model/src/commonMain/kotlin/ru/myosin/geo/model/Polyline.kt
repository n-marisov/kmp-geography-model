package ru.myosin.geo.model

import ru.myosin.geo.model.extensions.bounds.buildOrThrow
import ru.myosin.geo.model.extensions.bounds.coordinates

/**
 * Кривая линия из точек.
 */
@ConsistentCopyVisibility
data class Polyline private constructor( val coordinates: List<Location> ): Geometry() {
    override val bounds: Bounds by lazy { Bounds.Builder().coordinates(coordinates).buildOrThrow() }

    companion object Utils

    class Builder( var coordinates: MutableList<Location> = mutableListOf() ){
        constructor(block: Builder.() -> Unit):this(){apply(block)}

        operator fun not(): Boolean = coordinates.size < 2

        fun build(): Result<Polyline> {
            return if(not()) Result.failure(IllegalArgumentException(
                "Недостаточно точек для создания полилинии."
            ))
            else Result.success(Polyline(coordinates))
        }
    }
}