/*
 * Copyright 2019 Artear S.A.
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
package com.artear.stevedore.stevedoreviews.repository.impl.domain

import com.artear.networking.contract.Networking
import com.artear.networking.model.retrofit.executeWith
import com.artear.stevedore.stevedoreviews.repository.contract.action.Action
import com.artear.stevedore.stevedoreviews.repository.contract.api.StevedoreApi
import com.artear.stevedore.stevedoreviews.repository.contract.domain.StevedoreRepository

class StevedoreRepositoryImpl(private val action: Action,
                              private val stevedoreApi: StevedoreApi,
                              private val networking: Networking) : StevedoreRepository {

    override fun stevedore(param: Any?) = executeWith(networking) {
        val endpoint = action(param)
        stevedoreApi.getStevedore(endpoint)
    }


}