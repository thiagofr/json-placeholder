package com.thiagofr.jsonplaceholder.domain.mapper

import com.thiagofr.jsonplaceholder.data.User
import com.thiagofr.jsonplaceholder.model.UserUI

object UserToUserUIMapper {
    fun map(from: User) = UserUI(
        id = from.id,
        name = from.name,
        username = from.username,
        email = from.email,
        phone = from.phone,
        website = from.website,
        address = AddressToAddressUIMapper.map(from.address),
        company = CompanyToCompanyUIMapper.map(from.company),
    )
}