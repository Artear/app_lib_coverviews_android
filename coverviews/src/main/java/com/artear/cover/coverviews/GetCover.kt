package com.artear.cover.coverviews

import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coverviews.presentation.CoverDataShaper
import com.artear.cover.coverviews.presentation.CoverRegister
import com.artear.cover.coverviews.repository.contract.CoverRepository
import com.artear.domain.coroutine.UseCase

// TODO: REGISTRAR TIPOS DE DATA ( CONTENT / DFP / CATEGORY ) ....
class GetCover(coverRegister: CoverRegister, private val coverRepository: CoverRepository) :
        UseCase<Void, List<ArtearItem>>() {

    private val shaper = CoverDataShaper(coverRegister.shaperMap)

    override suspend fun execute(param: Void?): List<ArtearItem> {
        //Deserializers
        val stevedore = coverRepository.cover()

        ////////////////////////////

        //Shaper
        return shaper.transform(stevedore)
    }


}