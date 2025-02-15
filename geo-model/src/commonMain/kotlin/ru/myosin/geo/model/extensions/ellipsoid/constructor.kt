package ru.myosin.geo.model.extensions.ellipsoid

/***
 * Файл с конструкторами объекта Ellipsoid.
 * Все конструкторы выбрасывают исключение
 * в случае не возможности создать объект.
 */
import ru.myosin.geo.model.Ellipsoid.Utils
import ru.myosin.geo.model.Ellipsoid.Builder


operator fun Utils.invoke( block: Builder.() -> Unit ) = Builder(block).buildOrThrow()
operator fun Utils.invoke( equatorialRadius: Number, polarRadius: Number ) = Builder().polarRadius(polarRadius).equatorialRadius(equatorialRadius).buildOrThrow()
