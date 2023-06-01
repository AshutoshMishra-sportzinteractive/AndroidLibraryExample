package com.sportzinteractive.baseprojectsetup.helper

interface EntityMapper<E, D> {
    fun toDomain(entity: E): D
}