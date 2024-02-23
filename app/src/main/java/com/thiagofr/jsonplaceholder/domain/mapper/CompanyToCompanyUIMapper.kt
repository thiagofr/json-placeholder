package com.thiagofr.jsonplaceholder.domain.mapper

import com.thiagofr.jsonplaceholder.data.Company
import com.thiagofr.jsonplaceholder.model.CompanyUI

object CompanyToCompanyUIMapper {
    fun map(from: Company) = CompanyUI(
            name = from.name,
            catchPhrase = from.catchPhrase,
            bs = from.bs,
        )
}