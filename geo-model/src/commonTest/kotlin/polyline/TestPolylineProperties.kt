package polyline

import ru.myosin.geo.model.Location
import ru.myosin.geo.model.Polyline
import ru.myosin.geo.model.extensions.location.*
import ru.myosin.geo.model.extensions.polyline.*
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestPolylineProperties {

    val segments = listOf(
        Polyline( Location("47.235439, 39.644655"), Location("47.237544, 39.733576") ),
        Polyline( Location("45.235439, 39.654655"), Location("47.231544, 39.733576") ),
        Polyline( Location("47.232439, 39.644695"), Location("47.237844, 39.733476") )
    )

    val rings = listOf(
        Polyline( Location("47.235439, 39.644655"), Location("47.237544, 39.733576"), Location("47.235439, 39.644655") ),
        Polyline( Location("47.235439, 39.644655"), Location("47.257544, 39.7335786"), Location("47.235439, 39.644655") )
    )

    @Test
    fun testIsSegment(){
        segments.forEach {
            assertTrue { it.isSegment }
            assertFalse { it.isRing }
        }
    }

    @Test
    fun testIsRing(){
        rings.forEach {
            assertTrue { it.isRing }
            assertFalse { it.isSegment }
        }
    }
}