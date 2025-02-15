package bounds

import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.bounds.buildOrElse
import ru.myosin.geo.model.extensions.bounds.buildOrNull
import ru.myosin.geo.model.extensions.bounds.buildOrThrow
import ru.myosin.geo.model.extensions.bounds.coordinates
import ru.myosin.geo.model.extensions.bounds.east
import ru.myosin.geo.model.extensions.bounds.invoke
import ru.myosin.geo.model.extensions.bounds.north
import ru.myosin.geo.model.extensions.bounds.northEast
import ru.myosin.geo.model.extensions.bounds.northWest
import ru.myosin.geo.model.extensions.bounds.south
import ru.myosin.geo.model.extensions.bounds.southEast
import ru.myosin.geo.model.extensions.bounds.southWest
import ru.myosin.geo.model.extensions.bounds.west
import ru.myosin.geo.model.extensions.location.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TestBoundsBuilder {

    @Test
    fun testNorth(){
        val builder = Bounds.Builder(north = 2134.8)
        assertEquals(builder.north, 2134.8)

        builder.north(213L)
        assertEquals(builder.north, 213.0)

        builder.north("124.5")
        assertEquals(builder.north, 124.5)
    }

    @Test
    fun testWest(){
        val builder = Bounds.Builder(west = 2134.8)
        assertEquals(builder.west, 2134.8)

        builder.west(213L)
        assertEquals(builder.west, 213.0)

        builder.west("124.5")
        assertEquals(builder.west, 124.5)
    }

    @Test
    fun testSouth(){
        val builder = Bounds.Builder(south = 2134.8)
        assertEquals(builder.south, 2134.8)

        builder.south(213L)
        assertEquals(builder.south, 213.0)

        builder.south("124.5")
        assertEquals(builder.south, 124.5)
    }

    @Test
    fun testEast(){
        val builder = Bounds.Builder(east = 2134.8)
        assertEquals(builder.east, 2134.8)

        builder.east(213L)
        assertEquals(builder.east, 213.0)

        builder.east("124.5")
        assertEquals(builder.east, 124.5)
    }

    @Test
    fun testNorthWest(){
        val builder = Bounds.Builder().northWest(Location.invoke{
            latitude = 90.0
            longitude = 123.4
        })
        assertEquals(builder.north, 90.0)
        assertEquals(builder.west, 123.4)

        assertFails{
            builder.northWest("12.3d, 234")
        }

        builder.northWest("12.3, 123")
        assertEquals(builder.north, 12.3)
        assertEquals(builder.west, 123.0)

    }

    @Test
    fun testSouthEast(){
        val builder = Bounds.Builder().southEast(Location.invoke{
            latitude = 90.0
            longitude = 123.4
        })
        assertEquals(builder.south, 90.0)
        assertEquals(builder.east, 123.4)

        assertFails{
            builder.southEast("12.3d, 234")
        }

        builder.southEast("12.3, 123")
        assertEquals(builder.south, 12.3)
        assertEquals(builder.east, 123.0)

    }

    @Test
    fun testNorthEast(){
        val builder = Bounds.Builder().northEast(Location.invoke{
            latitude = 90.0
            longitude = 123.4
        })
        assertEquals(builder.north, 90.0)
        assertEquals(builder.east, 123.4)

        assertFails{
            builder.northEast("12.3d, 234")
        }

        builder.northEast("12.3, 123")
        assertEquals(builder.north, 12.3)
        assertEquals(builder.east, 123.0)

    }

    @Test
    fun testSouthWest(){
        val builder = Bounds.Builder().southWest(Location.invoke{
            latitude = 90.0
            longitude = 123.4
        })
        assertEquals(builder.south, 90.0)
        assertEquals(builder.west, 123.4)

        assertFails{
            builder.southWest("12.3d, 234")
        }

        builder.southWest("12.3, 123")
        assertEquals(builder.south, 12.3)
        assertEquals(builder.west, 123.0)

    }

    @Test
    fun testCoordinates(){
        val builder = Bounds.Builder()

        builder.coordinates(listOf(
            Location("47.493299, 38.945084"),
            Location("47.493299, 38.945084"),
            Location("46.726219, 40.336509"),
            Location("11.493823, 36.596650"),
            Location("36.282328, -78.100708"),
        ))


        val bounds = builder.buildOrNull()
        assertNotNull(bounds)
        assertEquals(bounds.north, 47.493299)
        assertEquals(bounds.west, -78.100708)
        assertEquals(bounds.south, 11.493823)
        assertEquals(bounds.east, 40.336509)

        val builder2 = Bounds.Builder().coordinates(listOf())
        assertTrue { !builder2 }

    }

    @Test
    fun testNot(){
        val builder = Bounds.Builder()
        builder.north(21)
        assertTrue { !builder }
        builder.west(21)
        assertTrue { !builder }
        builder.south(21)
        assertTrue { !builder }
        builder.east(21)
        assertFalse { !builder }
    }

    @Test
    fun testBuildResult(){
        val builder = Bounds.Builder()
        var result = builder.build()
        assertTrue { result.isFailure }

        builder.coordinates(listOf(
            Location("47.493299, 38.945084"),
            Location("47.493299, 38.945084"),
            Location("46.726219, 40.336509"),
            Location("11.493823, 36.596650"),
            Location("36.282328, -78.100708"),
        ))

        result = builder.build()
        assertTrue { result.isSuccess }
        val bounds = result.getOrNull()
        assertNotNull(bounds)
        assertEquals(bounds.north, 47.493299)
        assertEquals(bounds.west, -78.100708)
        assertEquals(bounds.south, 11.493823)
        assertEquals(bounds.east, 40.336509)

    }

    @Test
    fun testBuildOrElse(){

        val default = Bounds.invoke{
            north = 47.493299
            west = -78.100708
            south = 11.493823
            east = 40.336509
        }
        val builder = Bounds.Builder{}

        var bounds = builder.west(-11.9).buildOrElse { default }

        assertEquals(bounds.north, 47.493299)
        assertEquals(bounds.west, -78.100708)
        assertEquals(bounds.south, 11.493823)
        assertEquals(bounds.east, 40.336509)

        builder.north(12.3)
        builder.south(-23.4)
        builder.east(45.0)

        bounds = builder.buildOrElse { default }

        assertEquals(bounds.north, 12.3)
        assertEquals(bounds.west, -11.9)
        assertEquals(bounds.south, -23.4)
        assertEquals(bounds.east, 45.0)


        builder.north = null
        builder.west = null
        builder.south = null
        builder.east = null
        bounds = builder.west(-11.9).buildOrElse (default)

        assertEquals(bounds.north, 47.493299)
        assertEquals(bounds.west, -78.100708)
        assertEquals(bounds.south, 11.493823)
        assertEquals(bounds.east, 40.336509)

        builder.north(12.3)
        builder.south(-23.4)
        builder.east(45.0)

        bounds = builder.buildOrElse (default)

        assertEquals(bounds.north, 12.3)
        assertEquals(bounds.west, -11.9)
        assertEquals(bounds.south, -23.4)
        assertEquals(bounds.east, 45.0)


    }

    @Test
    fun testBuildOrNull(){
        val builder = Bounds.Builder()
        assertNull(builder.buildOrNull())
        builder.north("23.4")
        assertNull(builder.buildOrNull())
        builder.west("13.4")
        assertNull(builder.buildOrNull())
        builder.south("3.6")
        assertNull(builder.buildOrNull())
        builder.east("2.4")
        assertNotNull(builder.buildOrNull())

    }

    @Test
    fun testBuildOrThrow(){
        val builder = Bounds.Builder()

        assertFails { builder.buildOrThrow() }
        builder.north("23.4")
        assertFails { builder.buildOrThrow() }
        builder.west("13.4")
        assertFails { builder.buildOrThrow() }
        builder.south("3.6")
        assertFails { builder.buildOrThrow() }
        builder.east("2.4")

        val bounds = builder.buildOrThrow()

        assertEquals(bounds.north, 23.4)
        assertEquals(bounds.west, 2.4)
        assertEquals(bounds.south,3.6)
        assertEquals(bounds.east, 13.4)
    }
}