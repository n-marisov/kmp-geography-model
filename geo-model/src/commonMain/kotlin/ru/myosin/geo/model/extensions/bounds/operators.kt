package ru.myosin.geo.model.extensions.bounds

import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Location

operator fun Bounds.contains(bounds: Bounds): Boolean{
    return listOf(bounds.north, bounds.south).all { it in south..north } &&
    listOf(bounds.west, bounds.east).all { it in west..east }
}

operator fun Bounds.contains(location: Location): Boolean{
    return location.run { latitude in south..north && longitude in west..east }
}