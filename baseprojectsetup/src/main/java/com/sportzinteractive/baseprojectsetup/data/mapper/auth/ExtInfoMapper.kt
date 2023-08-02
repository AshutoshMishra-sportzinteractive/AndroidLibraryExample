package com.sportzinteractive.baseprojectsetup.data.mapper.auth

import com.sportzinteractive.baseprojectsetup.data.model.auth.ExtInfo
import com.sportzinteractive.baseprojectsetup.data.model.auth.ExtInfoEntity
import com.sportzinteractive.baseprojectsetup.helper.EntityDomainMapper
import javax.inject.Inject

class ExtInfoMapper @Inject constructor(): EntityDomainMapper<ExtInfoEntity?, ExtInfo?> {
    override fun toEntity(domain: ExtInfo?): ExtInfoEntity {
        return ExtInfoEntity(
            clubs = domain?.clubs
        )
    }

    override fun toDomain(entity: ExtInfoEntity?): ExtInfo {
        return ExtInfo(
            entity?.clubs
        )
    }
}