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