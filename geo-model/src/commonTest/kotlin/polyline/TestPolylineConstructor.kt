package polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline
import ru.myosin.geo.model.extensions.polyline.*
import ru.myosin.geo.model.extensions.location.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class TestPolylineConstructor {

    @Test
    fun testInitializeBlock(){
        assertFails {
            Polyline {  }
        }

        assertFails {
            Polyline { coordinates(mutableListOf(
                Location("20,30.4")
            )) }
        }

        val polyline = Polyline { coordinates(mutableListOf(
            Location("20,30.4"),
            Location("21,30.4"),
        )) }

        assertEquals(polyline.coordinates.size,2)
    }

    @Test
    fun testIterableLocation(){
        assertFails {
            Polyline(listOf<Location>())
        }

        assertFails {
            Polyline(
                listOf(Location("20,30.4"))
            )
        }

        val polyline = Polyline(listOf(
            Location("20,30.4"),
            Location("21,30.4"),
        ))

        assertEquals(polyline.coordinates.size,2)
    }

    @Test
    fun testVarargLocation(){
        assertFails {
            Polyline()
        }

        assertFails {
            Polyline(
                Location("20,30.4")
            )
        }

        val polyline = Polyline(
            Location("20,30.4"),
            Location("21,30.4"),
        )

        assertEquals(polyline.coordinates.size,2)
    }

}