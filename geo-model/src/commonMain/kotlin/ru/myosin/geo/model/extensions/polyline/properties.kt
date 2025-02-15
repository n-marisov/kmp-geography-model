package ru.myosin.geo.model.extensions.polyline

import ru.myosin.geo.model.Polyline

/**
 * Указывает что полилиния является прямым отрезком из двух точек.
 */
val Polyline.isSegment: Boolean get() = (coordinates.size == 2)

/**
 * Указывает что полилиния замкнута.
 */
val Polyline.isRing: Boolean get(){
    val first = coordinates.first()
    val last = coordinates.last()
    return first.latitude == last.latitude && first.longitude == last.longitude
}