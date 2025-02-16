package ru.myosin.geo.model.extensions.polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polygon.Builder
import ru.myosin.geo.model.Polygon.Utils


operator fun Utils.invoke( block:Builder.() -> Unit ) = Builder(block).buildOrThrow()
operator fun Utils.invoke(coordinates: Iterable<Iterable<Location>> ) = Builder().coordinates(coordinates).buildOrThrow()
operator fun Utils.invoke( vararg coordinates: Iterable<Location> ) = Builder().coordinates(coordinates.toMutableList()).buildOrThrow()