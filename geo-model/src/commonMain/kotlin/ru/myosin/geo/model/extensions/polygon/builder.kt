package ru.myosin.geo.model.extensions.polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polygon
import ru.myosin.geo.model.Polygon.Builder

fun Builder.buildOrElse( default: Polygon ) = build().getOrDefault(default)
fun Builder.buildOrElse( onFailure: (Throwable) -> Polygon ) = build().getOrElse(onFailure)
fun Builder.buildOrNull() = build().getOrNull()
fun Builder.buildOrThrow() = build().getOrThrow()

fun Builder.coordinates( value: Iterable<Iterable<Location>> ) = apply { coordinates = value.map { it.toMutableList() }.toMutableList() }




