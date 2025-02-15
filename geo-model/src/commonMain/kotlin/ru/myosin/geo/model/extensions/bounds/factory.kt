package ru.myosin.geo.model.extensions.bounds

/***
 * Файл с фабричными методами расширения объекта Bounds.
 * Все фабричные методы аналогичны методам конструкторам,
 * за исключением того что в случае ошибки они возвращают NULL
 * и не выбрасывают исключений.
 */
import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Bounds.Utils
import ru.myosin.geo.model.Bounds.Builder
import ru.myosin.geo.model.Location

fun Utils.of( block: Builder.() -> Unit ) = Builder(block).buildOrNull()
fun Utils.of( bounds: Bounds ) = Builder().north(bounds.north).west(bounds.west).south(bounds.south).east(bounds.east).buildOrNull()
fun Utils.of( north: Number, west: Number, south: Number, east: Number ) = Builder().north(north).west(west).south(south).east(east).buildOrNull()
fun Utils.of( north: String, west: String, south: String, east: String ) = Builder().north(north).west(west).south(south).east(east).buildOrNull()
fun Utils.of( one: Location,two: Location ) = Builder().northWest(one).southEast(two).buildOrNull()
fun Utils.of( location: Location ) = Builder().northWest(location).southEast(location).buildOrNull()
fun Utils.of( coordinates: Iterable<Location> ) = Builder().coordinates(coordinates).buildOrNull()
