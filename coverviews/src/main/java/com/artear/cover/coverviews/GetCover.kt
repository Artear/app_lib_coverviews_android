package com.artear.cover.coverviews

import com.artear.cover.coverviews.presentation.CoverDataShaper
import com.artear.cover.coverviews.repository.contract.CoverRepository
import com.artear.cover.coveritem.presentation.model.ArtearItem
import com.artear.cover.coveritem.presentation.model.ArtearObject
import com.artear.domain.coroutine.UseCase

// TODO: REGISTRAR TIPOS DE DATA ( CONTENT / DFP / CATEGORY ) ....
class GetCover(listDataAccepted: List<ArtearObject>, private val coverRepository: CoverRepository) :
        UseCase<Void, List<ArtearItem>>() {

    private val shaper = CoverDataShaper(listDataAccepted)

    override suspend fun execute(param: Void?): List<ArtearItem> {
        //Deserializers
        val stevedore = coverRepository.cover()



        ////////////////////////////

        //Shaper
        return shaper.transform(stevedore)
    }


}