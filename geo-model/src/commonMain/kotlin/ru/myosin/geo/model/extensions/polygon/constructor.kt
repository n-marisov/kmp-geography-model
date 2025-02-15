package ru.myosin.geo.model.extensions.polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline.Builder
import ru.myosin.geo.model.Polyline.Utils
import ru.myosin.geo.model.extensions.polyline.buildOrThrow
import ru.myosin.geo.model.extensions.polyline.coordinates

operator fun Utils.invoke( coordinates: Iterable<Location> ) = Builder().coordinates(coordinates).buildOrThrow()
operator fun Utils.invoke( vararg coordinates: Location ) = Builder().coordinates(coordinates.toMutableList()).buildOrThrow()