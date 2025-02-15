package ru.myosin.geo.model.extensions.location

/***
 * Файл с конструкторами объекта Location.
 * Все конструкторы выбрасывают исключение
 * в случае не возможности создать объект.
 */
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Location.Utils
import ru.myosin.geo.model.Location.Builder

operator fun Utils.invoke( block: Builder.() -> Unit ) = Builder(block).buildOrThrow()
operator fun Utils.invoke( location: Location ) = Builder().location(location).buildOrThrow()
operator fun Utils.invoke( location: String ) = Builder().location(location).buildOrThrow()
operator fun Utils.invoke( latitude: Number,longitude: Number ) = Builder().latitude(latitude).longitude(longitude).buildOrThrow()
operator fun Utils.invoke( latitude: String,longitude: String ) = Builder().latitude(latitude).longitude(longitude).buildOrThrow()
operator fun Utils.invoke( latitude: String,longitude: Number ) = Builder().latitude(latitude).longitude(longitude).buildOrThrow()
operator fun Utils.invoke( latitude: Number,longitude: String ) = Builder().latitude(latitude).longitude(longitude).buildOrThrow()