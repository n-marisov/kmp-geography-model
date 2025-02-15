package ru.myosin.geo.model.extensions.ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.Ellipsoid.Builder

private val equatorialSpheroid by lazy { Builder().equatorialRadius(6378.137).polarRadius(6378.137).buildOrThrow() }
private val polarSpheroid by lazy { Builder().equatorialRadius(6356.752314245).polarRadius(6356.752314245).buildOrThrow() }
private val spheroid by lazy { Builder().equatorialRadius(6371).polarRadius(6371).buildOrThrow() }
private val grs80 by lazy { Builder().equatorialRadius(6378.137).polarRadius(6356.752314140).buildOrThrow() }
private val wgs84 by lazy { Builder().equatorialRadius(6378.137).polarRadius(6356.752314245).buildOrThrow() }

val Ellipsoid.Utils.EQUATORIAL_SPHEROID get() = equatorialSpheroid
val Ellipsoid.Utils.POLAR_SPHEROID get() = polarSpheroid
val Ellipsoid.Utils.SPHEROID get() = spheroid
val Ellipsoid.Utils.GRS_80 get() = grs80
val Ellipsoid.Utils.WGS_84 get() = wgs84

