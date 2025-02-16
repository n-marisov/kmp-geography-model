package polygon

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polygon
import ru.myosin.geo.model.extensions.location.*
import ru.myosin.geo.model.extensions.polygon.buildOrElse
import ru.myosin.geo.model.extensions.polygon.buildOrNull
import ru.myosin.geo.model.extensions.polygon.buildOrThrow
import ru.myosin.geo.model.extensions.polygon.coordinates
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TestPolygonBuilder {

    @Test
    fun testCoordinates(){
        val builder = Polygon.Builder()

        assertTrue{ !builder }

        builder.coordinates = mutableListOf(
            mutableListOf(
                Location("21.3, 45.6"),
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
            ),
            mutableListOf(
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
                Location("21.3, 45.6"),
            )
        )

        assertFalse{ !builder }
    }

    @Test
    fun testBuildResult(){
        val builder = Polygon.Builder()

        var result = builder.build()

        assertFalse( result.isSuccess )
        assertTrue( result.isFailure )

        builder.coordinates( mutableListOf(
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
        ))

        result = builder.build()

        assertTrue( result.isSuccess )
        assertFalse( result.isFailure )

        val polygon = result.getOrNull()
        assertNotNull(polygon)

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)

    }

    @Test
    fun buildOrElse(){
        val builder = Polygon.Builder()

        builder.coordinates( mutableListOf(
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
        ))

        val default = builder.buildOrThrow()

        builder.coordinates = mutableListOf()

        var polygon = builder.buildOrElse { default }

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)

        builder.coordinates( mutableListOf(
            mutableListOf(
                Location("21.3, 45.6"),
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
            )
        ))

        polygon = builder.buildOrElse { default }

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 0)


        builder.coordinates = mutableListOf()
        polygon = builder.buildOrElse ( default )

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)

        builder.coordinates( mutableListOf(
            mutableListOf(
                Location("21.3, 45.6"),
                Location("12.4, 56.7"),
                Location("12.5, 67.8"),
            )
        ))

        polygon = builder.buildOrElse ( default )

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 0)
    }

    @Test
    fun buildOrNull(){
        val builder = Polygon.Builder()

        assertNull(builder.buildOrNull())

        builder.coordinates( mutableListOf(
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
        ))

        assertNotNull(builder.buildOrNull())
    }

    @Test
    fun buildOrThrow(){
        val builder = Polygon.Builder()

        assertFails { builder.buildOrThrow() }

        builder.coordinates( mutableListOf(
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
        ))

        val polygon = builder.buildOrThrow()

        assertEquals(polygon.contour.size, 3)
        assertEquals(polygon.holes.size, 2)
        assertEquals(polygon.holes[0].contour.size, 4)
        assertEquals(polygon.holes[1].contour.size, 6)
    }

}