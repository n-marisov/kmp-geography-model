package ru.myosin.geo.model.extensions.polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline
import ru.myosin.geo.model.Polyline.Builder

fun Builder.buildOrElse( default: Polyline ) = build().getOrDefault(default)
fun Builder.buildOrElse( onFailure: (Throwable) -> Polyline ) = build().getOrElse(onFailure)
fun Builder.buildOrNull() = build().getOrNull()
fun Builder.buildOrThrow() = build().getOrThrow()

fun Builder.coordinates( value: Iterable<Location> ) = apply { coordinates = value.toMutableList() }




