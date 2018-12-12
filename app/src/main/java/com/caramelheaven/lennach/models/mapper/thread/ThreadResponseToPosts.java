package com.caramelheaven.lennach.models.mapper.thread;

import android.text.Html;
import android.text.style.ClickableSpan;
import android.view.View;

import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.models.model.thread.CutIndexWord;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.models.network.FileResponse;
import com.caramelheaven.lennach.models.network.PostResponse;
import com.caramelheaven.lennach.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 00:49, 08/12/2018.
 */
public class ThreadResponseToPosts {

    public List<Post> map(List<PostResponse> value) {
        List<Post> posts = new ArrayList<>();
        fillPosts(posts, value);
        return posts;
    }


    private void fillPosts(List<Post> posts, List<PostResponse> value) {
        for (PostResponse response : value) {
            Post post = new Post();
            post.setBannned(response.getBannned());
            post.setDate(response.getDate());
            post.setEmail(response.getEmail());
            post.setName(response.getName());
            post.setOp(response.getOp());
            post.setTags(response.getTags());
            post.setLasthit(response.getLasthit());

            //Fill reply and answer from user post, start point and end point, 0..10 for exaomple. etc.
            String resComment = response.getComment();
//            if (resComment.contains(Constants.INSTANCE.getREPLY())) {
//                post.setCutIndexWordList(extraFilling(response));
//            }

            post.setComment(response.getComment());
            //post.setComment(response.getComment());

            //fill images in post
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

    //Fill data, html, [Reply >>] and etc.
    private List<CutIndexWord> extraFilling(PostResponse post) {
        List<CutIndexWord> cutIndexWords = new ArrayList<>();
        String comment = String.valueOf(post.getComment());
        String[] splitComment = comment.split("\\n");

        //Find all answered in user post
        for (String part : splitComment) {
            //if string contains >> - answered on post
            if (part.contains(Constants.INSTANCE.getREPLY())) {
                //TODO fix with (OP)
                Pattern word = Pattern.compile(part + "(\\s)");
                Matcher matcher = word.matcher(comment);

                if (part.contains("OP")) {
                    if (matcher.find()) {
                        //TODO feature + 5
                        cutIndexWords.add(new CutIndexWord(matcher.start(), matcher.end() - 1 + 5));
                    }
                } else {
                    if (matcher.find()) {
                        cutIndexWords.add(new CutIndexWord(matcher.start(), matcher.end() - 1));
                    }
                }
            }
        }

        Timber.d("comment extraFilling: " + comment);
        Timber.d("and result: " + cutIndexWords.toString());

        return cutIndexWords;
    }
}