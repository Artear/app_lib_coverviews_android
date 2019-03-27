package com.artear.cover.coverviews

import com.artear.cover.coverviews.repository.contract.CoverRepository
import com.artear.cover.coverviews.repository.model.ArtearItem
import com.artear.domain.coroutine.UseCase

// TODO: REGISTRAR TIPOS DE DATA ( CONTENT / DFP / CATEGORY ) ....
class GetCover(private val coverRepository: CoverRepository) : UseCase<Void, List<ArtearItem>>() {

    private val shaper = CoverDataShaper()

    override suspend fun execute(param: Void?): List<ArtearItem> {
        //Deserializers
        val cover = coverRepository.cover()

        ////////////////////////////

        //Shaper
        return shaper.transform(cover)
    }


}