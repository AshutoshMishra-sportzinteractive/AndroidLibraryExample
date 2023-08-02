package com.sportzinteractive.baseprojectsetup.data.mapper.auth

import com.sportzinteractive.baseprojectsetup.data.model.auth.User
import com.sportzinteractive.baseprojectsetup.data.model.auth.UserEntity
import com.sportzinteractive.baseprojectsetup.helper.EntityDomainMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils.DOB_DATE_FORMAT
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEntityMapper @Inject constructor(
    private val extInfoMapper: ExtInfoMapper,
): EntityDomainMapper<UserEntity, User> {
    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            firstName = domain.firstName,
            lastName = domain.lastName,
            dob = domain.dob,
            countryCode = domain.countryCode,
            mobileNo = domain.mobileNo,
            gender = domain.gender,
            countryId = domain.countryId,
            countryName = domain.countryName,
            city = domain.city,
            pinCode = domain.pinCode,
            favouritePlayerId = domain.favouritePlayerId,
            favouritePlayerName = domain.favouritePlayerName,
            favouriteClub = domain.favouriteClub,
            jerseyName = domain.jerseyName,
            jerseyNumber = domain.jerseyNumber,
            extInfo = extInfoMapper.toEntity(domain.extInfo),
            profileCompletionPercentage = domain.profileCompletionPercentage,
            socialUserImage = domain.socialUserImage,
            socialUserName = domain.socialUserName,
            subscribeForEmail = domain.subscribeForEmail,
            subscribeForWhatsapp = domain.subscribeWhatsApp,
            acceptTermsAndCondition = domain.termsAndCondition,
            state = domain.state,
            stateId = domain.stateId
        )
    }

    override fun toDomain(entity: UserEntity): User {
        return User(
            city = entity.city,
            countryId = entity.countryId,
            countryName = entity.countryName,
            dob = CalendarUtils.convertDateStringToSpecifiedDateString(entity.dob,DOB_DATE_FORMAT,requiredDateFormat = PUBLISHED_DISPLAY_DATE_FORMAT),
            favouritePlayerId = entity.favouritePlayerId,
            favouritePlayerName = entity.favouritePlayerName,
            favouriteClub = entity.favouriteClub,
            firstName = entity.firstName,
            gender = entity.gender,
            lastName = entity.lastName,
            countryCode = entity.countryCode,
            mobileNo = entity.mobileNo,
            pinCode = entity.pinCode,
            jerseyName = entity.jerseyName,
            jerseyNumber = entity.jerseyNumber,
            extInfo = extInfoMapper.toDomain(entity.extInfo),
            profileCompletionPercentage = entity.profileCompletionPercentage,
            socialUserImage = entity.socialUserImage,
            socialUserName = entity.socialUserName,
            subscribeForEmail = entity.subscribeForEmail,
            termsAndCondition = entity.acceptTermsAndCondition,
            subscribeWhatsApp = entity.subscribeForWhatsapp,
            email = null,
            password = null,
            confirmPassword = null,
            status = null,
            state = entity.state,
            stateId = entity.stateId
        )
    }

    fun toDomain(entity: UserEntity, email: String?,status:Int?): User {
        return toDomain(entity)
            .copy(
                email = email,
                status = status
            )
    }
}