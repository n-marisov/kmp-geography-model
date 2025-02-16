package ru.myosin.geo.model.extensions.polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polygon.Builder
import ru.myosin.geo.model.Polygon.Utils

fun Utils.of( block:Builder.() -> Unit ) = Builder(block).buildOrNull()
fun Utils.of(coordinates: Iterable<Iterable<Location>> ) = Builder().coordinates(coordinates).buildOrNull()
fun Utils.of( vararg coordinates: Iterable<Location> ) = Builder().coordinates(coordinates.toMutableList()).buildOrNull()