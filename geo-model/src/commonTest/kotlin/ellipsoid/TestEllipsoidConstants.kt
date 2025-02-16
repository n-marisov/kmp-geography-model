package ellipsoid

import ru.myosin.geo.model.Ellipsoid
import ru.myosin.geo.model.extensions.ellipsoid.EQUATORIAL_SPHEROID
import ru.myosin.geo.model.extensions.ellipsoid.GRS_80
import ru.myosin.geo.model.extensions.ellipsoid.POLAR_SPHEROID
import ru.myosin.geo.model.extensions.ellipsoid.SPHEROID
import ru.myosin.geo.model.extensions.ellipsoid.WGS_84
import kotlin.test.Test
import kotlin.test.assertEquals

class TestEllipsoidConstants {

    @Test
    fun testWgs84(){
        assertEquals(Ellipsoid.WGS_84.equatorialRadius,6378.137)
        assertEquals(Ellipsoid.WGS_84.polarRadius,6356.752314245)

        assertEquals(Ellipsoid.WGS_84.firstFlattening,0.003352810664775582)
        assertEquals(Ellipsoid.WGS_84.reverseFirstFlattening,298.25722356050017)

        assertEquals(Ellipsoid.WGS_84.secondFlattening,0.0033640898210047098)
        assertEquals(Ellipsoid.WGS_84.reverseSecondFlattening,297.25722356050017)

        assertEquals(Ellipsoid.WGS_84.thirdFlattening, 0.0016792203863978026)
        assertEquals(Ellipsoid.WGS_84.reverseThirdFlattening, 595.5144471210003)
    }

    @Test
    fun testGrs80(){
        assertEquals(Ellipsoid.GRS_80.equatorialRadius,6378.137)
        assertEquals(Ellipsoid.GRS_80.polarRadius,6356.752314140)

        assertEquals(Ellipsoid.GRS_80.firstFlattening,0.0033528106812381092)
        assertEquals(Ellipsoid.GRS_80.reverseFirstFlattening,298.257222096037)

        assertEquals(Ellipsoid.GRS_80.secondFlattening,0.0033640898375781863)
        assertEquals(Ellipsoid.GRS_80.reverseSecondFlattening,297.257222096037)

        assertEquals(Ellipsoid.GRS_80.thirdFlattening, 0.0016792203946567337)
        assertEquals(Ellipsoid.GRS_80.reverseThirdFlattening, 595.514444192074)
    }

    @Test
    fun testSpheroid(){
        assertEquals(Ellipsoid.SPHEROID.equatorialRadius,6371.0)
        assertEquals(Ellipsoid.SPHEROID.polarRadius,6371.0)

        assertEquals(Ellipsoid.SPHEROID.firstFlattening,0.0)
        assertEquals(Ellipsoid.SPHEROID.reverseFirstFlattening,0.0)

        assertEquals(Ellipsoid.SPHEROID.secondFlattening,0.0)
        assertEquals(Ellipsoid.SPHEROID.reverseSecondFlattening,0.0)

        assertEquals(Ellipsoid.SPHEROID.thirdFlattening, 0.0)
        assertEquals(Ellipsoid.SPHEROID.reverseThirdFlattening, 0.0)
    }

    @Test
    fun testEquatorialSpheroid(){
        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.equatorialRadius,6378.137)
        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.polarRadius,6378.137)

        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.firstFlattening,0.0)
        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.reverseFirstFlattening,0.0)

        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.secondFlattening,0.0)
        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.reverseSecondFlattening,0.0)

        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.thirdFlattening, 0.0)
        assertEquals(Ellipsoid.EQUATORIAL_SPHEROID.reverseThirdFlattening, 0.0)
    }

    @Test
    fun testPolarSpheroid(){
        assertEquals(Ellipsoid.POLAR_SPHEROID.equatorialRadius,6356.752314245)
        assertEquals(Ellipsoid.POLAR_SPHEROID.polarRadius,6356.752314245)

        assertEquals(Ellipsoid.POLAR_SPHEROID.firstFlattening,0.0)
        assertEquals(Ellipsoid.POLAR_SPHEROID.reverseFirstFlattening,0.0)

        assertEquals(Ellipsoid.POLAR_SPHEROID.secondFlattening,0.0)
        assertEquals(Ellipsoid.POLAR_SPHEROID.reverseSecondFlattening,0.0)

        assertEquals(Ellipsoid.POLAR_SPHEROID.thirdFlattening, 0.0)
        assertEquals(Ellipsoid.POLAR_SPHEROID.reverseThirdFlattening, 0.0)
    }
}