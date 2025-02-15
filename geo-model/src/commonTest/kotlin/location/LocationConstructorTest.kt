package location

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.location.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotSame


class LocationConstructorTest {

    @Test
    fun testBlockInitialize(){
        val location = Location{
            latitude = 20.1
            longitude = 30.4
        }

        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.4)

        assertFails {
            Location{
                latitude = 120.1
                longitude = 30.4
            }
        }

        assertFails {
            Location{
                latitude = 20.1
                longitude = 230.4
            }
        }
    }

    @Test
    fun testOfLocation(){
        val location = Location{
            latitude = 20.1
            longitude = 30.4
        }

        val expected = Location(location)

        assertNotSame(location,expected)
        assertEquals(location,expected)
        assertEquals(expected.latitude, 20.1)
        assertEquals(expected.longitude, 30.4)
    }

    @Test
    fun testOfString(){
        val location = Location(" 20.1, 30.4" )
        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.4)

        assertFails {
            Location(" 120.1, 30.4" )
        }
        assertFails {
            Location(" 20.1, 230.4" )
        }
    }

    @Test
    fun testOfNumbers(){
        val location = Location( 20.1f,  30L )

        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertFails {
            Location( 120.1f,  30L )
        }
        assertFails {
            Location( 20.1f,  230L )
        }
    }

    @Test
    fun testOfStrings(){
        val location = Location( "20.1",  "30" )

        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertFails {
            Location( "120.1",  "30" )
        }
        assertFails {
            Location( "20.1",  "230" )
        }
    }

    @Test
    fun testOfNumbersOrStrings(){
        val location = Location( 20.1f,  "30" )

        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertFails {
            Location( "120.1",  30 )
        }
        assertFails {
            Location( 20.1f,  "230" )
        }
    }

}