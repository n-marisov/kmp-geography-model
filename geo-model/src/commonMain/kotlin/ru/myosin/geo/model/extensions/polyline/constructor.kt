package ru.myosin.geo.model.extensions.polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline.Builder
import ru.myosin.geo.model.Polyline.Utils

operator fun Utils.invoke( block: Builder.() -> Unit ) = Builder(block).buildOrThrow()
operator fun Utils.invoke( coordinates: Iterable<Location> ) = Builder().coordinates(coordinates).buildOrThrow()
operator fun Utils.invoke( vararg coordinates: Location ) = Builder().coordinates(coordinates.toMutableList()).buildOrThrow()