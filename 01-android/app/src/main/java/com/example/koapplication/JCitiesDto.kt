package com.example.koapplication

class JCitiesDto(
    var name: String?,
    var state: String?,
    var country: String?,
    var capital: String?,
    var population: String?,
    var regions: List<String>?
) {
    override fun toString(): String{
        return "$name - $country"
    }
}