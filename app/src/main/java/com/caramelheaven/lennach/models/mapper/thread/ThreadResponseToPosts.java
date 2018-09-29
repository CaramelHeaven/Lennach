package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.mapper.Mapper;
import com.caramelheaven.lennach.models.model.common.DataSet;
import com.caramelheaven.lennach.models.model.thread_viewer.Post;
import com.caramelheaven.lennach.models.network.FileResponse;
import com.caramelheaven.lennach.models.network.PostResponse;
import com.caramelheaven.lennach.models.network.ThreadResponse;

import java.util.ArrayList;
import java.util.List;

public class ThreadResponseToPosts extends Mapper<ThreadResponse, List<Post>> {
    @Override
    public List<Post> map(ThreadResponse value) {
        List<Post> posts = new ArrayList<>();
        fillFiber(posts, value);
        return posts;
    }

    public ThreadResponse reverseMap(List<Post> valu) {
        return null;
    }

    private void fillFiber(List<Post> posts, ThreadResponse value) {
        for (PostResponse response : value.getPostsList()) {
            Post post = new Post();
            post.setBannned(response.getBannned());
            post.setComment(response.getComment());
            post.setDate(response.getDate());
            post.setEmail(response.getEmail());
            post.setName(response.getName());
            post.setOp(response.getOp());
            post.setTags(response.getTags());
            post.setLasthit(response.getLasthit());

            List<DataSet> dataList = new ArrayList<>();
            for (FileResponse file : response.getFiles()) {
                DataSet data = new DataSet();
                data.setDisplayNameImage(file.getDisplayname());
                data.setHeightImage(file.getHeight());
                data.setWidthImage(file.getWidth());
                data.setNameImage(file.getName());
                data.setSizeImage(file.getSize());
                data.setThumbnail(file.getThumbnail());
                dataList.add(data);
            }
            post.setFiles(dataList);
            posts.add(post);
        }
    }
}
