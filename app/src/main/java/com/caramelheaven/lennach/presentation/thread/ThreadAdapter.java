package com.caramelheaven.lennach.presentation.thread;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.models.model.thread.CutIndexWord;
import com.caramelheaven.lennach.models.model.thread.Post;
import com.caramelheaven.lennach.models.model.thread.SubComment;
import com.caramelheaven.lennach.utils.Constants;
import com.caramelheaven.lennach.utils.OnTextViewLinkClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList;

    //position for get item from postList and calculate ClickableSpans
    private int positionOnCreate = 0;

    private OnTextViewLinkClickListener onTextViewLinkClickListener;

    public ThreadAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);

        //Post post = postList.get(positionOnCreate);

        //TODO OPTIMIZATION, if 0 > not entered it here
        //List<CutIndexWord> cutIndexWords = new ArrayList<>();
        //provideExtraTextLinks(post, cutIndexWords);

        // positionOnCreate++;

        return new PostVH(view);
    }

    private void provideExtraTextLinks(Post post, List<CutIndexWord> cutIndexWords) {
        Timber.d("size: " + cutIndexWords.size());
        Timber.d("provideExtraTextLinks: ");
        SpannableString comment = SpannableString.valueOf(Html.fromHtml(post.getComment()));

        Timber.d("check comment: " + String.valueOf(comment) + " and it cutIndexWord: " + cutIndexWords.toString());
        for (CutIndexWord cutIndexWord : cutIndexWords) {
            int start = cutIndexWord.getStartIndex();
            int end = cutIndexWord.getEndIndex();
            Timber.d("ilm in for: " + start + " and end: " + end);

            ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Timber.d("clicked span");
                    onTextViewLinkClickListener.onLinkClink(
                            String.valueOf(comment.subSequence(start, end)));
                }
            };

            comment.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        post.setComment(String.valueOf(comment));

        Timber.d("after comment: " + post.getComment());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        PostVH postVH = (PostVH) viewHolder;
        //postVH.tvDescription.setText(Html.fromHtml(postList.get(position).getComment()));
//act=android.intent.action.VIEW dat=/b/res/187955383.html
        //comment.setSpan(span, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        String response = "<a href=\"/s/res/2419411.html#2438501\" class=\"post-reply-link\" " +
                "data-thread=\"2419411\" data-num=\"2438501\">>>2438501</a><br><span class=\"u" +
                "nkfunc\">&gt;А ты серийник диска сравнивал </span><br>Они мне сами сказали: <" +
                "em>Мы провели диагностику диска и все нормально.</em><br><br>Я сам его в авгу" +
                "сте покупал. Так что подумал ничего и страшного.<br><br><a href=\"/s/res/24194" +
                "11.html#2438529\" class=\"post-reply-link\" data-thread=\"2419411\" data-num=\"" +
                "2438529\">>>2438529</a><br>6 часов. Если бы действительно меняли, то 1-2 дня б" +
                "ы заняло.";

        String[] massive = String.valueOf(Html.fromHtml(response)).split("\n");

        List<SubComment> subComments = new ArrayList<>();
        List<String> replies = new ArrayList<>();

        boolean continueComment = false;

        StringBuilder userComment = new StringBuilder();

        //Parse html to simple SubComment list.
        //TODO refactoring this
        for (String data : massive) {
            Timber.d("dat: " + data);
            if (!data.isEmpty()) {
                //if data contains string like this: >>393939 omae wa mou shindeiru
                if (data.matches("^>>[0-9]*\\s[\\s*ЁёА-я]+")) {
                    Timber.d("зашел");
                    int firstWhitespace = data.indexOf(" ");

                    replies = new ArrayList<>();
                    userComment.setLength(0);

                    String reply = data.substring(0, firstWhitespace);
                    String description = data.substring(firstWhitespace + 1, data.length());

                    replies.add(reply);
                    userComment.append(description);

                    continueComment = true;
                }

                if (data.contains(Constants.INSTANCE.getREPLY())) {
                    if (continueComment) {
                        String temp = userComment.toString().trim();


                        subComments.add(new SubComment(replies, temp));

                        replies = new ArrayList<>();
                        userComment.setLength(0);

                        continueComment = false;
                    }

                    //if we have added this is the first state, don't add again
                    if (!data.matches("^>>[0-9]*\\s[\\s*ЁёА-я]+")) {
                        replies.add(data);
                    }
                } else {
                    if (userComment.length() > 0) {
                        userComment.append("\n");
                    }
                    userComment.append(data);

                    continueComment = true;
                }
            }
        }


        for (SubComment q : subComments) {
            Timber.d("check: " + q.toString());
        }

        //make big text
        StringBuilder bigText = new StringBuilder();

        for (SubComment comment : subComments) {
            for (String reply : comment.getRepliesList()) {
                if (comment.getRepliesList().size() > 1) {
                    bigText.append("\n");
                }
                bigText.append(reply);
            }

            bigText.append("\n");

            bigText.append(comment.getComment());

            bigText.append("\n");
            bigText.append("\n");
        }


        SpannableString spannable = new SpannableString(bigText.toString());

        Set<String> arara = new LinkedHashSet<>();

        for (SubComment comment : subComments) {
            arara.add(comment.getRepliesList().get(0));
        }

        Timber.d("check big text: " + bigText);

        Set<String> repls = new LinkedHashSet<>();

        for (SubComment comment : subComments) {
            repls.addAll(comment.getRepliesList());
        }

        for (String reply : repls) {
            Pattern word = null;
            int missingCharactersIndex = 0;
            if (reply.contains("(OP)")) {
                int mainIndex = reply.indexOf("(");
                String modifiedReply = reply.substring(0, mainIndex).trim();

                Timber.d("reply (OP): " + modifiedReply);

                missingCharactersIndex = reply.length() - modifiedReply.length();

                word = Pattern.compile(modifiedReply + "\\s+");
            } else {
                word = Pattern.compile(reply);
            }
            Timber.d("reply: " + reply);
            Matcher matcher = word.matcher(bigText);

            while (matcher.find()) {
                int endIndex = matcher.end();

                if (missingCharactersIndex != 0) {
                    endIndex += missingCharactersIndex;
                }

                Timber.d("find again: " + matcher.start() + " end: " + matcher.end() + " modified end: " + endIndex);
                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NotNull View widget) {
                        Timber.d("setOnItemClickListener: " + reply);
                    }
                }, matcher.start(), endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        //string.setSpan(span, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        postVH.tvDescription.setText(spannable);
        postVH.tvDescription.setMovementMethod(LinkMovementMethod.getInstance());

//        System.out.println("toString: " + postList.get(position).getCutIndexWordList().toString());

//        String
//
//        for (String spl : split) {
//            if (spl.contains(">>")) {
//                massiveLinks.add(spl);
//            }
//        }
//
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View textView) {
//                Timber.d("click");
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ss.setSpan(clickableSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(clickableSpan, 20, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        postVH.tvDescription.setText(ss);

        // ;
        // postVH.tvDate.setText(postList.get(i).getDate());
        //postVH.tvCountPost.setText(String.valueOf(i));
    }

    public void updateAdapter(List<Post> posts) {
        postList.addAll(posts);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void clear() {
        postList.clear();

        notifyDataSetChanged();
    }

    public class PostVH extends RecyclerView.ViewHolder {

        TextView tvDescription, tvDate, tvCountPost;
        ImageView ivPicture;

        /* Our array from CutIndexWord when we set highlight in text
         * */

        PostVH(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            // tvDate = itemView.findViewById(R.id.tv_date);
            // tvCountPost = itemView.findViewById(R.id.tv_count_post);
            ivPicture = itemView.findViewById(R.id.iv_picture_thread);
        }


    }

    public void setOnTextViewLinkClickListener(OnTextViewLinkClickListener
                                                       onTextViewLinkClickListener) {
        this.onTextViewLinkClickListener = onTextViewLinkClickListener;
    }
}
