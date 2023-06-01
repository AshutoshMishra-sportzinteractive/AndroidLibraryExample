package com.example.androidlibraryexample.data

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class ConfigManger @Inject constructor(
    //val firebaseRemoteConfig: FirebaseRemoteConfig
)
{

//    fun getBaseUrl():String{
//        return firebaseRemoteConfig.getString("base_url")
//    }
//
//    suspend fun fetchConfig(): Boolean {
//        return suspendCoroutine { continuation ->
//            firebaseRemoteConfig.fetchAndActivate()
//                .addOnSuccessListener {
//                    continuation.resume(true)
//                }
//                .addOnFailureListener {
//                    continuation.resume(false)
//                }
//        }
//    }

}