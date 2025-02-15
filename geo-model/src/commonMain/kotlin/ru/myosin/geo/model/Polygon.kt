package ru.myosin.geo.model

import ru.myosin.geo.model.extensions.bounds.buildOrThrow
import ru.myosin.geo.model.extensions.bounds.coordinates

@ConsistentCopyVisibility
data class Polygon private constructor(val contour: List<Location>, val holes: List<Polygon> ): Geometry() {
    override val bounds: Bounds by lazy { Bounds.Builder().coordinates(contour).buildOrThrow() }

    companion object Utils

    class Builder( var coordinates: MutableList<MutableList<Location>> ){

        operator fun not(): Boolean{
            return coordinates.isEmpty() || coordinates.first().isEmpty()
        }

        fun build(): Result<Polygon>{
            if(coordinates.isEmpty())
                return Result.failure(IllegalArgumentException())
            val contour = coordinates.first()

            if(contour.isEmpty())
                return Result.failure(IllegalArgumentException())

            if(coordinates.size == 1)
                return Result.success( Polygon( contour, emptyList() ) )

            val holes = mutableListOf<Polygon>()
            for (i in 1..coordinates.size)
                holes.add(Polygon(coordinates[i], emptyList()))

            return Result.success( Polygon( contour, holes ) )
        }
    }

}