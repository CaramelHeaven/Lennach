package com.caramelheaven.lennach.domain.thread_use_case;

import com.caramelheaven.lennach.domain.base.BaseUseCase;
import com.caramelheaven.lennach.models.model.board.Usenet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by CaramelHeaven on 20:36, 13/01/2019.
 * get favourite Usenets or [Threads] which user saved in the database
 */
public class GetFavouriteUsenets extends BaseUseCase<Single<List<Usenet>>> {
    @Override
    public Single<List<Usenet>> subscribeToData() {
        return null;
    }
}
