package com.thiagofr.jsonplaceholder.data

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company,
)
