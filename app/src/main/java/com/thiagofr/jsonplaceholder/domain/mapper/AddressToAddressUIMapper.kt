package com.thiagofr.jsonplaceholder.domain.mapper

import com.thiagofr.jsonplaceholder.data.Address
import com.thiagofr.jsonplaceholder.model.AddressUI

object AddressToAddressUIMapper {
    fun map(from: Address) = AddressUI(
        street = from.street,
        suite = from.suite,
        city = from.city,
        zipcode = from.zipcode,
    )
}