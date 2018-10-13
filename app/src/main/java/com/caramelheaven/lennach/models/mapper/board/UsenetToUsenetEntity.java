package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import timber.log.Timber;

public class UsenetToUsenetEntity extends Mapper<Usenet, UsenetEntity> {
    @Override
    public UsenetEntity map(Usenet value) {
        UsenetEntity entity = new UsenetEntity();
        fillData(entity, value);
        return entity;
    }

    private void fillData(UsenetEntity entity, Usenet usenet) {
        Timber.d("usenet - getThreadNum: " + usenet.getThreadNum());
        entity.setThreadNum(usenet.getThreadNum());
        entity.setComment(usenet.getComment());
        entity.setDate(usenet.getDate());
        entity.setFilesCount(usenet.getFilesCount());
        entity.setPostsCount(usenet.getPostsCount());
        Timber.d("checking entity: " + entity.toString());
    }
}
