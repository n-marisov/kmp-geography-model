package polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline
import ru.myosin.geo.model.extensions.location.*
import ru.myosin.geo.model.extensions.polyline.buildOrElse
import ru.myosin.geo.model.extensions.polyline.buildOrNull
import ru.myosin.geo.model.extensions.polyline.buildOrThrow
import ru.myosin.geo.model.extensions.polyline.coordinates
import ru.myosin.geo.model.extensions.polyline.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


class TestPolylineBuilder {

    @Test
    fun testInitializeBlock(){
        val builder = Polyline.Builder{
            coordinates = mutableListOf(
                Location("47.226319, 39.679674")
            )
        }

        assertTrue { !builder }

        builder.coordinates.add(Location("47.207605, 39.671434"))

        assertFalse { !builder }
    }

    @Test
    fun testCoordinates(){
        val builder = Polyline.Builder().coordinates(listOf(Location("47.207605, 39.671434")))
        assertTrue { !builder }
        builder.coordinates(listOf(
            Location("47.226319, 39.679674"),
            Location("47.207605, 39.671434")
        ))
        assertFalse { !builder }
        assertEquals(builder.coordinates.size, 2)
    }

    @Test
    fun testBuildOrElse(){
        val default = Polyline.invoke(
            Location("47.207605, 39.671434"),
            Location("47.207605, 39.671434")
        )

        val builder = Polyline.Builder().coordinates(listOf(Location("47.207605, 39.671434")))

        var polyline = builder.buildOrElse(default)
        assertEquals(polyline.coordinates.size,2)

        polyline = builder.buildOrElse{ default }
        assertEquals(polyline.coordinates.size,2)

        builder.coordinates(listOf(
            Location("47.207605, 39.671434"),
            Location("47.207605, 39.671434"),
            Location("47.255078, 39.741129"),
            Location("47.200118, 39.809450")
        ))

        polyline = builder.buildOrElse{ default }
        assertEquals(polyline.coordinates.size,4)

    }

    @Test
    fun testBuildOrNull(){
        val builder = Polyline.Builder().coordinates(listOf(Location("47.207605, 39.671434")))

        var polyline = builder.buildOrNull()
        assertNull(polyline)

        builder.coordinates(listOf(
            Location("47.207605, 39.671434"),
            Location("47.207605, 39.671434"),
            Location("47.255078, 39.741129"),
            Location("47.200118, 39.809450")
        ))
        polyline = builder.buildOrNull()

        assertNotNull(polyline)

    }

    @Test
    fun testBuildOrThrow(){
        val builder = Polyline.Builder().coordinates(listOf(Location("47.207605, 39.671434")))

        assertFails {
            builder.buildOrThrow()
        }


        builder.coordinates(listOf(
            Location("47.207605, 39.671434"),
            Location("47.207605, 39.671434"),
            Location("47.255078, 39.741129"),
            Location("47.200118, 39.809450")
        ))

        val polyline = builder.buildOrThrow()
        assertEquals(polyline.coordinates.size,4)

    }

    @Test
    fun testBuildResult(){
        val builder = Polyline.Builder().coordinates(listOf(Location("47.207605, 39.671434")))

        var polyline = builder.build()

        assertTrue { polyline.isFailure }

        builder.coordinates(listOf(
            Location("47.207605, 39.671434"),
            Location("47.207605, 39.671434"),
            Location("47.255078, 39.741129"),
            Location("47.200118, 39.809450")
        ))

        polyline = builder.build()
        assertTrue { polyline.isSuccess }
        assertEquals(polyline.getOrThrow().coordinates.size,4)
    }

}