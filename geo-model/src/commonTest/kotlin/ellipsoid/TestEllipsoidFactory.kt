package ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.extensions.ellipsoid.invoke
import ru.myosin.geo.model.extensions.ellipsoid.of
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TestEllipsoidFactory {

    @Test
    fun testBlockInitializer(){
        assertNull(Ellipsoid.of{})

        assertNull(
            Ellipsoid.of{ polarRadius = 12.3 }
        )

        assertNull(
            Ellipsoid.of { equatorialRadius = 12.3 }
        )

        var success = Ellipsoid.of {
            polarRadius = 112.3
            equatorialRadius = 12.3
        }

        assertNotNull(success)
        assertEquals(success.equatorialRadius, 12.3)
        assertEquals(success.polarRadius, 112.3)
    }

    @Test
    fun testPolarAndEquatorialRadius(){

        var success = Ellipsoid.of(
            polarRadius = 112.3,
            equatorialRadius = 12.3
        )

        assertNotNull(success)
        assertEquals(success.equatorialRadius, 12.3)
        assertEquals(success.polarRadius, 112.3)
    }
}