package com.artear.stevedore.stevedoreviews

import com.artear.domain.coroutine.UseCase
import com.artear.stevedore.stevedoreviews.presentation.PageParam
import com.artear.stevedore.stevedoreviews.repository.model.Paging

abstract class PagingUseCase<Param, Return> : UseCase<PageParam<Param>, Pair<Return, Paging?>>()