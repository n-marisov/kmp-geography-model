package bounds

import ru.myosin.geo.model.Bounds
import ru.myosin.geo.model.Location
import ru.myosin.geo.model.extensions.bounds.*
import ru.myosin.geo.model.extensions.location.*
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestBoundsContains {

    val bounds = Bounds(
        Location("51.525215, 35.287727"),
        Location("46.493422, 48.793659")
    )


    @Test
    fun testBounds(){
        assertTrue {  Bounds(
            Location("50.054415, 40.578711"),
            Location("48.721436, 44.152446")
        ) in bounds }

        assertFalse {  Bounds(
            Location("47.320403, 37.376273"),
            Location("42.988740, 52.831516")
        ) in bounds }
    }

    @Test
    fun testLocation(){

    }
}