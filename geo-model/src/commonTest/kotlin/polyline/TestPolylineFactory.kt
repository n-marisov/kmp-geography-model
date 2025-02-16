package polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline
import ru.myosin.geo.model.extensions.polyline.*
import ru.myosin.geo.model.extensions.location.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TestPolylineFactory {

    @Test
    fun testInitializeBlock(){

        var polyline = Polyline.of {  }

        assertNull(polyline)

        polyline = Polyline.of { coordinates(mutableListOf(
            Location("20,30.4")
        )) }

        assertNull(polyline)

        polyline = Polyline.of { coordinates(mutableListOf(
            Location("20,30.4"),
            Location("21,30.4"),
        )) }

        assertNotNull(polyline)
        assertEquals(polyline.coordinates.size,2)
    }

    @Test
    fun testIterableLocation(){
        var polyline = Polyline.of(listOf<Location>())

        assertNull(polyline)
        polyline = Polyline.of(
                listOf(Location("20,30.4"))
            )

        assertNull(polyline)
        polyline = Polyline.of(listOf(
            Location("20,30.4"),
            Location("21,30.4"),
        ))

        assertNotNull(polyline)
        assertEquals(polyline.coordinates.size,2)
    }

    @Test
    fun testVarargLocation(){
        var polyline = Polyline.of()

        polyline = Polyline.of(
                Location("20,30.4")
            )

        polyline = Polyline.of(
            Location("20,30.4"),
            Location("21,30.4"),
        )

        assertNotNull(polyline)
        assertEquals(polyline.coordinates.size,2)
    }

}