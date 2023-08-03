package com.sportzinteractive.baseprojectsetup.helper

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sportzinteractive.baseprojectsetup.data.model.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseLocalStorageManager @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    val gson: Gson,
) {

    private val isCompleteProfile = booleanPreferencesKey("isCompleteProfile")

    private val userGuidKey = stringPreferencesKey("user_guid")

    private val epochTimestamp = stringPreferencesKey("epoch_timestamp")

    private val userWafId = stringPreferencesKey("userWafId")

    private val userTokenKey = stringPreferencesKey("user_token")

    private val tokenKey = stringPreferencesKey("token")


    private val userInfoKey = stringPreferencesKey("user")

    private val emailVerified = booleanPreferencesKey("emailVerified")

    private val mobileVerified = booleanPreferencesKey("mobileVerified")





    suspend fun setCompleteProfile(isComplete: Boolean) {
        dataStore.edit { prefs -> prefs[isCompleteProfile] = isComplete }
    }

    fun getCompleteProfile(): Flow<Boolean?> {
        return dataStore.data.map { prefs -> prefs[isCompleteProfile]?:false }
    }

    suspend fun setUserGuid(userGuid: String) {
        dataStore.edit { prefs -> prefs[userGuidKey] = userGuid }
    }

    fun getUserGuid(): Flow<String?> {
        return dataStore.data.map { prefs -> prefs[userGuidKey] }
    }

    suspend fun setUserWafId(userGuid: String) {
        dataStore.edit { prefs -> prefs[userWafId] = userGuid }
    }

    fun getUserWafId(): Flow<String?> {
        return dataStore.data.map { prefs -> prefs[userWafId] }
    }

    fun getEpocTimeStamp(): Flow<String?> {
        return dataStore.data.map { prefs -> prefs[epochTimestamp] }
    }

    suspend fun setEpocTimeStamp(epocTimeStamp: String) {
        dataStore.edit { prefs -> prefs[epochTimestamp] = epocTimeStamp }
    }



    suspend fun setUserToken(token: String) {
        dataStore.edit { prefs -> prefs[userTokenKey] = token }
    }

    fun getUserToken(): Flow<String?> {
        return dataStore.data.map { prefs -> prefs[userTokenKey] }
    }

    suspend fun setToken(token: String) {
        dataStore.edit { prefs -> prefs[tokenKey] = token }
    }

    fun getToken(): Flow<String?> {
        return dataStore.data.map { prefs -> prefs[tokenKey] }
    }

    suspend fun setUser(user: User) {
        dataStore.edit { prefs -> prefs[userInfoKey] = gson.toJson(user) }
    }

    fun getUser(): Flow<User?> {
        return dataStore.data.map { prefs ->
            try {
                gson.fromJson<User>(prefs[userInfoKey], TypeToken.get(User::class.java).type)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun setEmailVerified(case: Boolean) {
        dataStore.edit { prefs ->
            prefs[emailVerified] = case
        }
    }

    fun getIsEmailVerified(): Flow<Boolean?> {
        return dataStore.data.map { prefs ->
            prefs[emailVerified]
        }
    }

    suspend fun setMobileVerified(case: Boolean) {
        dataStore.edit { prefs ->
            prefs[mobileVerified] = case
        }
    }

    fun getIsMobileVerified(): Flow<Boolean?> {
        return dataStore.data.map { prefs ->
            prefs[mobileVerified]
        }
    }

    suspend fun removeAll() {
        removeUserWafId()
        removeUserGuid()
        removeUserToken()
        removeUser()
        removeProfile()
    }

    suspend fun removeUser() {
        dataStore.edit { prefs -> prefs.remove(userInfoKey) }
    }

    private suspend fun removeUserWafId() {
        dataStore.edit { prefs -> prefs.remove(userWafId) }
    }

    suspend fun removeUserGuid() {
        dataStore.edit { prefs -> prefs.remove(userGuidKey) }
    }

    suspend fun removeUserToken() {
        dataStore.edit { prefs -> prefs.remove(userTokenKey) }
    }
    suspend fun removeProfile() {
        dataStore.edit { prefs -> prefs.remove(isCompleteProfile) }
    }



    suspend fun isLoggedIn(): Boolean {
        return getUser().firstOrNull() != null
                && getUserGuid().firstOrNull() != null
                && getUserToken().firstOrNull() != null
                && getCompleteProfile().firstOrNull() == true
    }
}