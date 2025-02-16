package ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.extensions.ellipsoid.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class TestEllipsoidConstructor {

    @Test
    fun testBlockInitializer(){
        assertFails {
            Ellipsoid {}
        }

        assertFails {
            Ellipsoid {
                polarRadius = 12.3
            }
        }

        assertFails {
            Ellipsoid {
                equatorialRadius = 12.3
            }
        }

        var success = Ellipsoid {
            polarRadius = 112.3
            equatorialRadius = 12.3
        }

        assertEquals(success.equatorialRadius, 12.3)
        assertEquals(success.polarRadius, 112.3)
    }

    @Test
    fun testPolarAndEquatorialRadius(){

        var success = Ellipsoid(
            polarRadius = 112.3,
            equatorialRadius = 12.3
        )

        assertEquals(success.equatorialRadius, 12.3)
        assertEquals(success.polarRadius, 112.3)
    }
}