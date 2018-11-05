package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.database.UsenetEntity;
import com.caramelheaven.lennach.models.model.board_viewer.Usenet;

import java.util.ArrayList;
import java.util.List;

public class UsenetEntityToUsenet {

    public List<Usenet> map(List<UsenetEntity> value) {
        List<Usenet> usenets = new ArrayList<>();
        fillData(usenets, value);
        return usenets;
    }

    private void fillData(List<Usenet> usenets, List<UsenetEntity> usenetEntities) {
        for (UsenetEntity entity : usenetEntities) {
            Usenet usenet = new Usenet();
            usenet.setComment(entity.getComment());
            usenet.setDate(entity.getDate());
            usenet.setPostsCount(entity.getPostsCount());
            usenet.setFilesCount(entity.getFilesCount());
            usenet.setThreadNum(entity.getThreadNum());
            usenets.add(usenet);
        }
    }
}
