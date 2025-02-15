package ru.myosin.geo.model

import ru.myosin.geo.model.extensions.bounds.buildOrThrow
import ru.myosin.geo.model.extensions.bounds.northWest
import ru.myosin.geo.model.extensions.bounds.southEast

/**
 * Географическая координата.
 */
@ConsistentCopyVisibility
data class Location private constructor(val latitude: Double, val longitude: Double): Geometry(){

    override val bounds: Bounds by lazy { Bounds.Builder().northWest(this).southEast(this).buildOrThrow() }

    companion object Utils
    class Builder( var latitude:Double? = null, var longitude: Double? = null ){
        constructor(block: Builder.() -> Unit):this(){apply(block)}

        private fun isValidLatitude(): Boolean{
            return latitude?.let { it in -90.0..90.0 } == true
        }

        private fun isValidLongitude(): Boolean{
            return longitude?.let { it in -180.0..180.0 } == true
        }

        operator fun not(): Boolean = !isValidLongitude() || !isValidLatitude()

        fun build(): Result<Location> = if( not() ){
                Result.failure<Location>(IllegalArgumentException(when{
                    listOf(latitude,longitude).all { it == null } -> "The builder is empty."
                    latitude == null -> "Latitude is not initialized."
                    latitude!! !in -90.0..90.0 -> "The latitude value does not correspond to the range of -90..90."
                    longitude == null -> "Longitude is not initialized."
                    longitude!! !in -180.0..180.0 -> "The longitude value does not correspond to the range of -180..180."
                    else -> "Unknown error"
                }))
            }else Result.success<Location>(Location( latitude!!,longitude!! ))
    }
}

