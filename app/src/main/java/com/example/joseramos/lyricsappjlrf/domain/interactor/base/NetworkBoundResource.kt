/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.joseramos.lyricsappjlrf.domain.interactor.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.example.joseramos.lyricsappjlrf.AppExecutors
import com.example.joseramos.lyricsappjlrf.domain.interactor.result.Resource
import com.example.joseramos.lyricsappjlrf.domain.models.base.BaseModel

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkBoundResource<DiskType : BaseModel, CloudType : BaseModel> @MainThread internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<DiskType>>()

    fun init(loading: DiskType) {

        result.setValue(Resource.loading(loading))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData -> setValue(Resource.success(newData!!)) }
            }
        }

    }

    @MainThread
    private fun setValue(newValue: Resource<DiskType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<DiskType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData -> setValue(Resource.loading(newData!!)) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            if (response != null && !response.error) {
                appExecutors.diskIO.execute({
                    saveCallResult(processResponse(response))
                    appExecutors.mainThread.execute {
                        // we specially request a new live data,
                        // otherwise we will get immediately last cached value,
                        // which may not be updated with latest results received from network.
                        result.addSource(loadFromDb()) { newData -> setValue(Resource.success(newData!!)) }
                    }
                })
            } else {
                result.addSource(dbSource, { newData -> setValue(Resource.error(response!!.message, newData!!)) })
            }
        }
    }

    protected fun onFetchFailed() {
    }

    fun asLiveData(): LiveData<Resource<DiskType>> {
        return result
    }

    @WorkerThread
    protected open fun processResponse(response: CloudType): CloudType {
        return response
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: CloudType)

    @MainThread
    protected abstract fun shouldFetch(data: DiskType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<DiskType>

    @MainThread
    protected abstract fun createCall(): LiveData<CloudType>
}
