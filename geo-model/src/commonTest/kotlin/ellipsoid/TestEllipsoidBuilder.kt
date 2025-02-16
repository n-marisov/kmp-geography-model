package ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.extensions.ellipsoid.GRS_80
import ru.myosin.geo.model.extensions.ellipsoid.WGS_84
import ru.myosin.geo.model.extensions.ellipsoid.buildOrElse
import ru.myosin.geo.model.extensions.ellipsoid.buildOrNull
import ru.myosin.geo.model.extensions.ellipsoid.buildOrThrow
import ru.myosin.geo.model.extensions.ellipsoid.equatorialRadius
import ru.myosin.geo.model.extensions.ellipsoid.f
import ru.myosin.geo.model.extensions.ellipsoid.polarRadius
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TestEllipsoidBuilder {

    @Test
    fun testInitEquatorialRadius(){
        val builder = Ellipsoid.Builder()

        assertNull(builder.equatorialRadius)
        builder.equatorialRadius(12.9)
        assertEquals(builder.equatorialRadius, 12.9)

        builder.equatorialRadius = 129.5
        assertEquals(builder.equatorialRadius, 129.5)

        builder.equatorialRadius("12.9")
        assertEquals(builder.equatorialRadius, 12.9)
    }

    @Test
    fun testInitPolarRadius(){
        val builder = Ellipsoid.Builder()

        assertNull(builder.polarRadius)
        builder.polarRadius(12.9)
        assertEquals(builder.polarRadius, 12.9)

        builder.polarRadius = 129.5
        assertEquals(builder.polarRadius, 129.5)

        builder.polarRadius("12.9")
        assertEquals(builder.polarRadius, 12.9)
    }

    @Test
    fun testNot(){
        val builder = Ellipsoid.Builder()

        assertTrue { !builder }

        builder.equatorialRadius("12.9")

        assertTrue { !builder }

        builder.polarRadius(12.9)

        assertFalse { !builder }
    }

    @Test
    fun testBuildResult(){
        val builder = Ellipsoid.Builder()
        var result = builder.build()

        assertNull(result.getOrNull())
        builder.apply {
            polarRadius = 1234.5
             equatorialRadius = 678.9
        }

        result = builder.build()
        assertNotNull(result.getOrNull())

    }

    @Test
    fun testBuildOrElse(){
        val builder = Ellipsoid.Builder()

        var result = builder.buildOrElse (Ellipsoid.WGS_84)
        assertEquals(result.reverseFirstFlattening.toFloat(), 298.257223563f)

        result = builder.buildOrElse {Ellipsoid.GRS_80}
        assertEquals(result.reverseFirstFlattening.toFloat(), 298.257222100882711f)

    }

    @Test
    fun buildOrNull(){
        val builder = Ellipsoid.Builder()

        assertNull(builder.buildOrNull())

        builder.polarRadius = 298.257223563

        assertNull(builder.buildOrNull())

        builder.equatorialRadius = 298.257222100882711

        assertNotNull(builder.buildOrNull())
    }

    @Test
    fun buildOrThrow(){
        val builder = Ellipsoid.Builder()

        assertFails { builder.buildOrThrow() }

        builder.polarRadius = 298.257223563

        assertFails { builder.buildOrThrow() }

        builder.equatorialRadius = 298.257222100882711

        assertNotNull(builder.buildOrNull())
    }
}