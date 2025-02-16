package polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polygon
import ru.myosin.geo.model.extensions.location.invoke
import ru.myosin.geo.model.extensions.polygon.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class TestPolygonConstructor {

    @Test
    fun testBlockInitialize(){
        assertFails { Polygon {  } }

        val polygon = Polygon {
            coordinates = mutableListOf(
                mutableListOf(
                    Location("21.3, 45.6"),
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                ),
                mutableListOf(
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                ),

                mutableListOf(
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                ),
            )
        }

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)
        assertEquals(polygon.holes[1].contour[1].longitude, 67.8)
    }

    @Test
    fun testIterableCoordinates(){
        assertFails { Polygon(listOf<Iterable<Location>>()) }

        val polygon = Polygon ( mutableListOf(
                mutableListOf(
                    Location("21.3, 45.6"),
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                ),
                mutableListOf(
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                ),

                mutableListOf(
                    Location("12.4, 56.7"),
                    Location("12.5, 67.8"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                    Location("21.3, 45.6"),
                ),
            )
        )

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)
        assertEquals(polygon.holes[1].contour[1].longitude, 67.8)
    }

    @Test
    fun testVarargCoordinates(){
        assertFails { Polygon() }

        val polygon = Polygon (
            mutableListOf(
                Location("21.3, 45.6"),
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
            ),
            mutableListOf(
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
                Location("21.3, 45.6"),
                Location("21.3, 45.6"),
            ),

            mutableListOf(
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
                Location("21.3, 45.6"),
                Location("21.3, 45.6"),
                Location("21.3, 45.6"),
                Location("21.3, 45.6"),
            ),
        )

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)
        assertEquals(polygon.holes[1].contour[1].longitude, 67.8)
    }
}