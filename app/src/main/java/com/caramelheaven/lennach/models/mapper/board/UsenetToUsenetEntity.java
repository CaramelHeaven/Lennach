package com.caramelheaven.lennach.models.mapper.board;

import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

public class UsenetToUsenetEntity {

    public UsenetEntity map(Usenet value, boolean isFavourite) {
        UsenetEntity entity = new UsenetEntity();
        fillData(entity, value, isFavourite);
        return entity;
    }

    private void fillData(UsenetEntity entity, Usenet usenet, boolean isFavourite) {
        entity.setThreadNum(usenet.getThreadNum());
        entity.setComment(usenet.getComment());
        entity.setDate(usenet.getDate());
        entity.setFilesCount(usenet.getFilesCount());
        entity.setPostsCount(usenet.getPostsCount());
        entity.setFavourite(isFavourite);
    }
}
