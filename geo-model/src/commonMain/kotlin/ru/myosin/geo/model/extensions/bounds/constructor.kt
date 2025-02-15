package ru.myosin.geo.model.extensions.bounds

/***
 * Файл с конструкторами объекта Bounds.
 * Все конструкторы выбрасывают исключение
 * в случае не возможности создать объект.
 */
import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Bounds.Utils
import ru.myosin.geo.model.Bounds.Builder
import ru.myosin.geo.model.Location

operator fun Utils.invoke( block: Builder.() -> Unit ) = Builder(block).buildOrThrow()
operator fun Utils.invoke( bounds: Bounds ) = Builder().north(bounds.north).west(bounds.west).south(bounds.south).east(bounds.east).buildOrThrow()
operator fun Utils.invoke( north: Number, west: Number, south: Number, east: Number ) = Builder().north(north).west(west).south(south).east(east).buildOrThrow()
operator fun Utils.invoke( north: String, west: String, south: String, east: String ) = Builder().north(north).west(west).south(south).east(east).buildOrThrow()
operator fun Utils.invoke( one: Location,two: Location ) = Builder().northWest(one).southEast(two).buildOrThrow()
operator fun Utils.invoke( location: Location ) = Builder().northWest(location).southEast(location).buildOrThrow()
operator fun Utils.invoke( coordinates: Iterable<Location> ) = Builder().coordinates(coordinates).buildOrThrow()

