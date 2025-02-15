package ru.myosin.geo.model.extensions.ellipsoid

/***
 * Файл с конструкторами объекта Ellipsoid.
 * Все конструкторы выбрасывают исключение
 * в случае не возможности создать объект.
 */
import ru.myosin.geo.model.Ellipsoid.Utils
import ru.myosin.geo.model.Ellipsoid.Builder


fun Utils.of( block: Builder.() -> Unit ) = Builder(block).buildOrNull()
fun Utils.of( equatorialRadius: Number, polarRadius: Number ) = Builder().polarRadius(polarRadius).equatorialRadius(equatorialRadius).buildOrNull()
