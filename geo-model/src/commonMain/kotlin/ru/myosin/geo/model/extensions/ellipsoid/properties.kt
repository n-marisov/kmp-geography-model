package ru.myosin.geo.model.extensions.ellipsoid

import ru.myosin.geo.model.Ellipsoid

/***
 * Элиас экваториального радиуса.
 */
val Ellipsoid.a get() = equatorialRadius

/***
 * Элиас полярного радиуса.
 */
val Ellipsoid.b get() = polarRadius

/***
 * Элиас коэффициента сплющивания.
 */
val Ellipsoid.f get() = reverseFirstFlattening

/**
 * Указывает что Эллипсоид является сфероидом.
 */
val Ellipsoid.isSpheroid get() = (equatorialRadius == polarRadius)

/**
 * Указывает что Эллипсоид является сплющенным.
 */
val Ellipsoid.isFlattened get() = (equatorialRadius > polarRadius)

/**
 * Указывает что Эллипсоид является вытянутым.
 */
val Ellipsoid.isElongated get() = (equatorialRadius < polarRadius)
