package com.thiagofr.jsonplaceholder.model

data class UserUI(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressUI,
    val phone: String,
    val website: String,
    val company: CompanyUI,
)
