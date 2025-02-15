package location

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.location.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class LocationFactoryTest {

    @Test
    fun testBlockInitialize(){
        val location = Location.of{
            latitude = 20.1
            longitude = 30.4
        }
        assertNotNull(location)

        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.4)

        assertNull(
            Location.of{
                latitude = 120.1
                longitude = 30.4
            }
        )

        assertNull(
            Location.of{
                latitude = 20.1
                longitude = 230.4
            }
        )
    }

    @Test
    fun testOfString(){
        val location = Location.of(" 20.1, 30.4" )
        assertNotNull(location)
        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.4)

        assertNull(Location.of(" 120.1, 30.4" ))
        assertNull(Location.of(" 20.1, 230.4" ))
    }

    @Test
    fun testOfNumbers(){
        val location = Location.of( 20.1f,  30L )
        assertNotNull(location)
        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertNull(Location.of( 120.1f,  30L ))
        assertNull(Location.of( 20.1f,  230L ))
    }

    @Test
    fun testOfStrings(){
        val location = Location.of( "20.1",  "30" )
        assertNotNull(location)
        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertNull(Location.of( "120.1",  "30" ))
        assertNull(Location.of( "20.1",  "230" ))
    }

    @Test
    fun testOfNumbersOrStrings(){
        val location = Location.of( 20.1f,  "30" )
        assertNotNull(location)
        assertEquals(location.latitude, 20.1)
        assertEquals(location.longitude, 30.0)

        assertNull(Location.of( "120.1",  30 ))
        assertNull(Location.of( 20.1f,  "230" ))
    }

}