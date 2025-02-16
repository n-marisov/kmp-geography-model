package ru.myosin.geo.model.extensions.ellipsoid

/***
 * В файле собраны методы расширения строителя объекта Location.
 */
import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.Ellipsoid.Builder


fun Builder.buildOrElse( default: Ellipsoid ) = build().getOrDefault(default)
fun Builder.buildOrElse( onFailure: (Throwable) -> Ellipsoid ) = build().getOrElse(onFailure)
fun Builder.buildOrNull() = build().getOrNull()
fun Builder.buildOrThrow() = build().getOrThrow()

fun Builder.equatorialRadius(value: Number) = apply { equatorialRadius = value.toString().toDouble() }
fun Builder.equatorialRadius(value: String) = apply { equatorialRadius = value.toDouble() }

fun Builder.polarRadius(value: Number) = apply { polarRadius = value.toString().toDouble() }
fun Builder.polarRadius(value: String) = apply { polarRadius = value.toDouble() }

