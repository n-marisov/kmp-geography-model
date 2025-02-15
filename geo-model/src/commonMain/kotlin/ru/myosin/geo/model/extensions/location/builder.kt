package ru.myosin.geo.model.extensions.location

/***
 * В файле собраны методы расширения строителя объекта Location.
 */
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Location.Builder

fun Builder.buildOrElse( default: Location ) = build().getOrDefault(default)
fun Builder.buildOrElse( onFailure: (Throwable) -> Location ) = build().getOrElse(onFailure)
fun Builder.buildOrNull() = build().getOrNull()
fun Builder.buildOrThrow() = build().getOrThrow()

fun Builder.latitude( value: Number ) = apply { latitude = value.toDouble() }
fun Builder.latitude( value: String ) = apply { latitude = value.toDouble() }

fun Builder.longitude( value: Number ) = apply { longitude = value.toDouble() }
fun Builder.longitude( value: String ) = apply { longitude = value.toDouble() }

fun Builder.location( value: Location ) = apply {
    latitude = value.latitude
    longitude = value.longitude
}
fun Builder.location( value: String ) = apply {
    value.split(",").map { it.trim().toDouble() }.takeIf { it.size == 2 }?.let {
        latitude = it.first()
        longitude = it.last()
    }
}