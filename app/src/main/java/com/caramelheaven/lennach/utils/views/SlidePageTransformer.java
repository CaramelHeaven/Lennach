package com.caramelheaven.lennach.utils.views;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.caramelheaven.lennach.R;

/**
 * Created by CaramelHeaven on 22:03, 07/12/2018.
 */
public class SlidePageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();

        //Get thread page and it position
        if ((int) page.getTag() == 1 && position < .70f && position > .001f) {
            // get real numbers, because right fragment has number 1, left -1, middle 0
            float current = 1f - position;
            current *= 100;

            //Set real number from user touch
            float tranX = 100 - current;
            tranX *= -1;

            page.setTranslationX(tranX);
        } else if ((int) page.getTag() == 1 && position > .70f) {
            //if user faster scroll, we should put some limiter
            page.setTranslationX(-70);
        }

        if ((int) page.getTag() == 0) {
            RelativeLayout relativeLayout = page.findViewById(R.id.relativeLayout);
            relativeLayout.setTranslationX(-position * (pageWidth / 2));
        } else if ((int) page.getTag() == 1) {
//            ImageView imageView = page.findViewById(R.id.image1);
//            imageView.setTranslationX(-position * (pageWidht / 2));
            //page.setTranslationX(-100);

        }
    }
}
