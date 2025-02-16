package ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.extensions.ellipsoid.SPHEROID
import ru.myosin.geo.model.extensions.ellipsoid.WGS_84
import ru.myosin.geo.model.extensions.ellipsoid.a
import ru.myosin.geo.model.extensions.ellipsoid.b
import ru.myosin.geo.model.extensions.ellipsoid.f
import ru.myosin.geo.model.extensions.ellipsoid.invoke
import ru.myosin.geo.model.extensions.ellipsoid.isElongated
import ru.myosin.geo.model.extensions.ellipsoid.isFlattened
import ru.myosin.geo.model.extensions.ellipsoid.isSpheroid
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestEllipsoidProperties {

    @Test
    fun testA(){
        assertEquals(Ellipsoid.WGS_84.a, 6378.137)
    }

    @Test
    fun testB(){
        assertEquals(Ellipsoid.WGS_84.b, 6356.752314245)
    }

    @Test
    fun testF(){
        assertEquals(Ellipsoid.WGS_84.f, 298.25722356050017)
    }

    @Test
    fun testIsSpheroid(){
        assertTrue { Ellipsoid.SPHEROID.isSpheroid }
        assertFalse { Ellipsoid.SPHEROID.isFlattened }
        assertFalse { Ellipsoid.SPHEROID.isElongated }
    }

    @Test
    fun testIsFlattened(){
        assertTrue { Ellipsoid.WGS_84.isFlattened }
        assertFalse { Ellipsoid.WGS_84.isSpheroid }
        assertFalse { Ellipsoid.WGS_84.isElongated }
    }

    @Test
    fun testIsElongated(){
        val ellipsoid = Ellipsoid.invoke(12,15)
        assertTrue { ellipsoid.isElongated }
        assertFalse { ellipsoid.isSpheroid }
        assertFalse { ellipsoid.isFlattened }
    }


}