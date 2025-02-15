package ru.myosin.geo.model.extensions.bounds

/***
 * В файле собраны методы расширения строителя объекта Location.
 */
import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Bounds.Builder
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.location.location

fun Builder.buildOrElse( default: Bounds ) = build().getOrDefault(default)
fun Builder.buildOrElse( onFailure: (Throwable) -> Bounds ) = build().getOrElse(onFailure)
fun Builder.buildOrNull() = build().getOrNull()
fun Builder.buildOrThrow() = build().getOrThrow()

fun Builder.north( value: Number ) = apply { north = value.toString().toDouble() }
fun Builder.north( value: String ) = apply { north = value.toDouble() }

fun Builder.west( value: Number ) = apply { west = value.toString().toDouble() }
fun Builder.west( value: String ) = apply { west = value.toDouble() }

fun Builder.south( value: Number ) = apply { south = value.toString().toDouble() }
fun Builder.south( value: String ) = apply { south = value.toDouble() }

fun Builder.east( value: Number ) = apply { east = value.toString().toDouble() }
fun Builder.east( value: String ) = apply { east = value.toDouble() }

fun Builder.northWest( value: Location ) = apply {
    north = value.latitude
    west = value.longitude
}

fun Builder.northWest( value: String ) = apply {
    Location.Builder().location(value).build().apply {
        northWest(getOrThrow())
    }
}

fun Builder.southEast( value: Location ) = apply {
    south = value.latitude
    east = value.longitude
}

fun Builder.southEast( value: String ) = apply {
    Location.Builder().location(value).build().apply {
        southEast(getOrThrow())
    }
}

fun Builder.northEast( value: Location ) = apply {
    north = value.latitude
    east = value.longitude
}

fun Builder.northEast( value: String ) = apply {
    Location.Builder().location(value).build().apply {
        northEast(getOrThrow())
    }
}

fun Builder.southWest( value: Location ) = apply {
    south = value.latitude
    west = value.longitude
}

fun Builder.southWest( value: String ) = apply {
    Location.Builder().location(value).build().apply {
        southWest(getOrThrow())
    }
}

fun Builder.coordinates( value: Iterable<Location> ) = apply {
    val latitudes = value.map { it.latitude }.toMutableList().apply {
        north?.also { add(it) }
        south?.also { add(it) }
    }
    val longitudes = value.map { it.longitude }.toMutableList().apply {
        west?.also { add(it) }
        east?.also { add(it) }
    }

    north = if(latitudes.isEmpty()) null else latitudes.max()
    west = if(longitudes.isEmpty()) null else longitudes.min()
    south = if(latitudes.isEmpty()) null else latitudes.min()
    east = if(longitudes.isEmpty()) null else longitudes.max()
}
