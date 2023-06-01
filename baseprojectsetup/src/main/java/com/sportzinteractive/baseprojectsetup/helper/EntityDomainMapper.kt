package com.sportzinteractive.baseprojectsetup.helper

interface EntityDomainMapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}