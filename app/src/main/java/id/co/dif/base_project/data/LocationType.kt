package id.co.dif.base_project.data

import android.util.Log

sealed class LocationType(
    val name: String
) {

    object TtSiteAll : LocationType("TT Site All")
    object Site : LocationType("Site")
    object Technician : LocationType("technician")
    object TtMapAll : LocationType("TT Map All")
    object Note : LocationType("note")

    object Kapal : LocationType("Kapal")
    object Apartement : LocationType("Apartement")

    object Excavator : LocationType("Excavator")

    companion object {
        fun fromString(type: String): LocationType {
            return when (type.lowercase()) {
                "tt site all" -> TtSiteAll
                "site" -> Site
                "technician" -> Technician
                "tt map all" -> TtMapAll
                "note" -> Note
                "kapal" -> Kapal
                "apartement" -> Apartement
                "excavator" -> Excavator
                else -> {
                    Log.w("TAG", "MarkerTripleE Type: $type is not recognized!")
                    Site
                }
            }
        }
    }
}