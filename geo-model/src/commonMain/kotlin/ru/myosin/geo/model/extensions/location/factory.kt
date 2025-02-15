package ru.myosin.geo.model.extensions.location

/***
 * Файл с фабричными методами расширения объекта Location.
 * Все фабричные методы аналогичны методам конструкторам,
 * за исключением того что в случае ошибки они возвращают NULL
 * и не выбрасывают исключений.
 */
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Location.Utils
import ru.myosin.geo.model.Location.Builder

fun Utils.of( block: Builder.() -> Unit ) = Builder(block).buildOrNull()
fun Utils.of( location: Location ) = Builder().location(location).buildOrNull()
fun Utils.of( location: String ) = Builder().location(location).buildOrNull()
fun Utils.of( latitude: Number,longitude: Number ) = Builder().latitude(latitude).longitude(longitude).buildOrNull()
fun Utils.of( latitude: String,longitude: String ) = Builder().latitude(latitude).longitude(longitude).buildOrNull()
fun Utils.of( latitude: String,longitude: Number ) = Builder().latitude(latitude).longitude(longitude).buildOrNull()
fun Utils.of( latitude: Number,longitude: String ) = Builder().latitude(latitude).longitude(longitude).buildOrNull()