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
package com.artear.stevedore.stevedoreviews

import com.artear.domain.coroutine.UseCase
import com.artear.stevedore.stevedoreitems.presentation.model.ArtearItem
import com.artear.stevedore.stevedoreviews.presentation.StevedoreDataShaper
import com.artear.stevedore.stevedoreviews.presentation.StevedoreRegister
import com.artear.stevedore.stevedoreviews.repository.contract.domain.StevedoreRepository

class GetStevedore(stevedoreRegister: StevedoreRegister, private val stevedoreRepository: StevedoreRepository) :
        UseCase<Void, List<ArtearItem>>() {

    private val shaper = StevedoreDataShaper(stevedoreRegister.shaperMap)

    override suspend fun execute(param: Void?): List<ArtearItem> {
        val stevedore = stevedoreRepository.stevedore()
        return shaper.transform(stevedore)
    }

}