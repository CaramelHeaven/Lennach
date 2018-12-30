package com.caramelheaven.lennach.models.mapper.thread;

import android.annotation.SuppressLint;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import com.caramelheaven.lennach.models.model.common.DataImage;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.models.network.FileResponse;
import com.caramelheaven.lennach.models.network.PostResponse;
import com.caramelheaven.lennach.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
            post.setNum(response.getNum());
            post.setName(response.getName());
            post.setOp(response.getOp());
            post.setTags(response.getTags());
            post.setLasthit(response.getLasthit());

            //handling to modern text with selectable links
            post.setComment(response.getComment());
            post.setModernComment(addedReferencesToAnswered(post, posts));

            //check if post contains replies from all posts in thread


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

    /* Selected text, for example : >>303003 and set on it click listener
     * */
    private SpannableString addedReferencesToAnswered(Post currentPost, List<Post> postList) {
        String comment = String.valueOf(Html.fromHtml(currentPost.getComment()));

        SpannableString spannableString = new SpannableString(comment);
        String[] lines = comment.split("\n");

        for (String line : lines) {
            //op reply
            if (line.contains(Constants.INSTANCE.getREPLY()) && line.contains("(OP)")) {
                String foundLine = line.substring(0, line.length() - 5);
                Pattern word = Pattern.compile(foundLine);
                Matcher matcher = word.matcher(spannableString);

                while (matcher.find()) {
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NotNull View widget) {
                            currentPost.onAnswerItemClickListener.onAnswerClick(line);
                        }
                    }, matcher.start(), matcher.end() + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                //Fake post for find it in the list<Post>
                Post postFinder = new Post();
                postFinder.setNum(foundLine.replaceAll("[^0-9]", ""));

                grabRepliesToPost(postFinder, currentPost, postList);
            } else if (line.contains(Constants.INSTANCE.getREPLY())) {
                //simple reply
                Pattern word = Pattern.compile(line);
                Matcher matcher = word.matcher(spannableString);

                while (matcher.find()) {
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NotNull View widget) {
                            currentPost.onAnswerItemClickListener.onAnswerClick(line);
                        }
                    }, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                //Fake post for find it in the list<Post>
                Post postFinder = new Post();
                postFinder.setNum(line.replaceAll("[^0-9]", ""));

                grabRepliesToPost(postFinder, currentPost, postList);
            }
        }

        return spannableString;
    }

    private void grabRepliesToPost(Post fakePost, Post currentPost, List<Post> postList) {
        if (postList.size() > 0 && postList.contains(fakePost)) {
            Post foundPost = postList.get(postList.indexOf(fakePost));

            if (foundPost.getRepliesPostList() == null) {
                List<String> strings = new ArrayList<>();
                strings.add(currentPost.getNum());

                foundPost.setRepliesPostList(strings);
            } else {
                foundPost.getRepliesPostList().add(currentPost.getNum());
            }
        }
    }
}