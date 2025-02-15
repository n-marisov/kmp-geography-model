package location

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.location.*
import ru.myosin.geo.model.extensions.location.invoke
import ru.myosin.geo.model.extensions.location.latitude
import ru.myosin.geo.model.extensions.location.longitude
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LocationBuilderTest {

    @Test
    fun addLatitude(){
        val builder = Location.Builder(latitude = 21.3)
        assertEquals(builder.latitude, 21.3)

        builder.latitude(2131)
        assertEquals(builder.latitude, 2131.0)

        builder.latitude("12.1")
        assertEquals(builder.latitude, 12.1)

        assertFails {
            builder.latitude("12abc")
        }

    }

    @Test
    fun addLongitude(){
        val builder = Location.Builder(longitude = 21.3)
        assertEquals(builder.longitude, 21.3)

        builder.longitude(2131)
        assertEquals(builder.longitude, 2131.0)

        builder.longitude("12.1")
        assertEquals(builder.longitude, 12.1)

        assertFails {
            builder.longitude("12abc")
        }
    }

    @Test
    fun testAddLocation(){
        val location = Location {
            latitude = 50.1
            longitude = 123.456789
        }
        val builder = Location.Builder().location(location)
        assertEquals(builder.latitude, 50.1)
        assertEquals(builder.longitude, 123.456789)

    }

    @Test
    fun testOfStringLocation(){

        val builder = Location.Builder().location(" 50.1, 123.456789")
        assertEquals(builder.latitude, 50.1)
        assertEquals(builder.longitude, 123.456789)

    }

    @Test
    fun testNot(){
        val builder = Location.Builder(50.1, 123.456789)
        assertFalse { builder.not() }
        assertFalse { !builder }
        builder.latitude("213")
        assertTrue { builder.not() }
        assertTrue { !builder }
    }

    @Test
    fun testBuildResult(){
        val builder = Location.Builder(50.1, 123.456789)
        val result: Result<Location> = builder.build()
        assertTrue { result.isSuccess }
        val location = result.getOrThrow()
        assertEquals(location.latitude, 50.1)
        assertEquals(location.longitude, 123.456789)

        builder.latitude(200)



        val failed = builder.build()
        assertTrue { failed.isFailure }

        assertFails{
            failed.getOrThrow()
        }

        builder.latitude(12)
        builder.longitude(1234)

        val failed2 = builder.build()
        assertTrue { failed2.isFailure }

        assertFails{
            failed2.getOrThrow()
        }
    }

    @Test
    fun testBuildOrDefault(){
        val location = Location.Builder().buildOrElse {
            Location("12,34.0")
        }
        assertEquals(location.latitude, 12.0)
        assertEquals(location.longitude, 34.0)

        val location2 = Location.Builder().latitude(23).longitude("2.8").buildOrElse {
            Location("12,34.0")
        }
        assertEquals(location2.latitude, 23.0)
        assertEquals(location2.longitude, 2.8)

        val location3 = Location.Builder().buildOrElse (
            Location("12,34.0")
        )
        assertEquals(location3.latitude, 12.0)
        assertEquals(location3.longitude, 34.0)

        val location4 = Location.Builder().latitude(23).longitude("2.8").buildOrElse (
            Location("12,34.0")
        )
        assertEquals(location4.latitude, 23.0)
        assertEquals(location4.longitude, 2.8)
    }

    @Test
    fun testBuildOrNull(){
        val builder = Location.Builder{
            latitude = 21.1
            longitude = 23.6
        }

        assertNotNull(builder.buildOrNull())
        builder.latitude = 123.4
        assertNull(builder.buildOrNull())
    }

    @Test
    fun testBuildOrThrow(){
        val builder = Location.Builder{
            latitude = 21.1
            longitude = 23.6
        }

        assertNotNull(builder.buildOrNull())
        builder.latitude = 123.4
        assertFails {
            builder.buildOrThrow()
        }

    }
}