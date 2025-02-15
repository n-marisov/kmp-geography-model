package ru.myosin.geo.model.extensions.polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline.Builder
import ru.myosin.geo.model.Polyline.Utils

fun Utils.of( coordinates: Iterable<Location> ) = Builder().coordinates(coordinates).buildOrNull()
fun Utils.of( vararg coordinates: Location ) = Builder().coordinates(coordinates.toMutableList()).buildOrNull()