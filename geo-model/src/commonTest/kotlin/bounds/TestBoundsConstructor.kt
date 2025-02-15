package bounds

import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.bounds.*
import ru.myosin.geo.model.extensions.location.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame

class TestBoundsConstructor {

    @Test
    fun testBlockInitializer(){
        assertFails { Bounds{} }
        assertFails { Bounds{
            north =21.1
        } }
        assertFails { Bounds{
            north = 21.4
            west = 181.5
            south = 21.1
            east = 12.4
        } }

        val success = Bounds{
            north = 21.4
            west = -81.5
            south = -21.1
            east = 12.4
        }
        assertEquals(success.north, 21.4)
        assertEquals(success.west, -81.5)
        assertEquals(success.south, -21.1)
        assertEquals(success.east, 12.4)
    }

    @Test
    fun testOfBounds(){
        assertFails { Bounds( Bounds{
            north = 21.4
            west = 181.5
            south = 21.1
            east = 12.4
        }) }

        val success = Bounds{
            north = 21.4
            west = -81.5
            south = -21.1
            east = 12.4
        }

        val bounds = Bounds(success)
        assertNotSame(bounds,success)
        assertEquals(bounds,success)
        assertEquals(bounds.north, 21.4)
        assertEquals(bounds.west, -81.5)
        assertEquals(bounds.south, -21.1)
        assertEquals(bounds.east, 12.4)
    }

    @Test
    fun testOfNumbers(){
        assertFails { Bounds(
            north = 21.4,
            west = 181.5,
            south = 21.1,
            east = 12.4,
        ) }

        val success = Bounds(
            north = 21.4,
            west = -81.5,
            south = -21.1,
            east = 12.4,
        )
        assertEquals(success.north, 21.4)
        assertEquals(success.west, -81.5)
        assertEquals(success.south, -21.1)
        assertEquals(success.east, 12.4)
    }

    @Test
    fun testOfStrings(){
        assertFails { Bounds(
            north = "21.4",
            west = "181.5",
            south = "21.1",
            east = "12.4",
        ) }

        val success = Bounds(
            north = "21.4",
            west = "-81.5",
            south = "-21.1",
            east = "12.4",
        )
        assertEquals(success.north, 21.4)
        assertEquals(success.west, -81.5)
        assertEquals(success.south, -21.1)
        assertEquals(success.east, 12.4)
    }

    @Test
    fun testOfLocations(){
        val one = Location.invoke("21.4, -81.5")
        val two = Location.invoke("-21.1,12.4")

        val bounds = Bounds(one,two)

        assertEquals(bounds.north, 21.4)
        assertEquals(bounds.west, -81.5)
        assertEquals(bounds.south, -21.1)
        assertEquals(bounds.east, 12.4)
    }

    @Test
    fun testOfLocation(){
        val one = Location.invoke("21.4, -81.5")

        val bounds = Bounds(one)

        assertEquals(bounds.north, 21.4)
        assertEquals(bounds.west, -81.5)
        assertEquals(bounds.south, 21.4)
        assertEquals(bounds.east, -81.5)
    }

    @Test
    fun testCoordinates(){

        assertFails { Bounds(listOf<Location>()) }

        val coordinates = listOf(
            Location("47.493299, 38.945084"),
            Location("47.493299, 38.945084"),
            Location("46.726219, 40.336509"),
            Location("11.493823, 36.596650"),
            Location("36.282328, -78.100708"),
        )

        val bounds = Bounds(coordinates)

        assertEquals(bounds.north, 47.493299)
        assertEquals(bounds.west, -78.100708)
        assertEquals(bounds.south, 11.493823)
        assertEquals(bounds.east, 40.336509)
    }
}