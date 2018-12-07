package com.caramelheaven.lennach.models.mapper.thread;

import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.models.network.FileResponse;
import com.caramelheaven.lennach.models.network.PostResponse;
import com.caramelheaven.lennach.models.network.ThreadResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 00:49, 08/12/2018.
 */
public class ThreadResponseToPosts {

    public List<Post> map(ThreadResponse value) {
        List<Post> posts = new ArrayList<>();
        fillPosts(posts, value);
        return posts;
    }


    private void fillPosts(List<Post> posts, ThreadResponse value) {
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

            List<DataImage> dataList = new ArrayList<>();
            for (FileResponse file : response.getFiles()) {
                DataImage data = new DataImage();
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