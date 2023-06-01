package com.example.androidlibraryexample.data

import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getFakeStuff(id:Int): Flow<Resource<UserItem>>
}