package com.mrdo.example.appframework.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.mrdo.example.appframework.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by dulijie on 2018/9/10.
 * 其他特殊效果参照
 * https://www.cnblogs.com/qianyukun/p/6867436.html
 */
public class GlideUtils {
//    Crop
//    默认：CropTransformation,
//    圆形：CropCircleTransformation,
//    方形：CropSquareTransformation,
//    圆角：RoundedCornersTransformation
//
//            Color
//    颜色覆盖：ColorFilterTransformation,
//    置灰：GrayscaleTransformation
//a、预加载代码
//Glide.with(this)
//     .load(url)
//     .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//     .preload();

    public static void setImage(Activity activity, ImageView imageView, String url) {
        GlideApp.with(activity)
                .asBitmap()//.asGif()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()//是否显示动画
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
//                .priority( Priority.HIGH )//设置加载优先级
//                .dontAnimate() //不显示动画效果
                .into(imageView);
    }

    public static void setImage(Fragment fragment, ImageView imageview, String url) {
        GlideApp.with(fragment)
                .asBitmap()//.asGif()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()//是否显示动画
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
//                .priority( Priority.HIGH )//设置加载优先级
//                .dontAnimate() //不显示动画效果
                .into(imageview);
    }

    public static void setImageRes(Context context, ImageView imageView, @DrawableRes int resId) {
        GlideApp.with(context)
                .load(resId)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(imageView);
    }

    public static void setImage(final Context context, final ImageView view, final String url) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            } else {
                GlideApp.with(context)
                        .load(url)
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter()
                        .into(view);
            }
        } else if (context != null) {
            GlideApp.with(context)
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(view);
        }
    }

    public static void setCircleImage(Context context, final ImageView imageView, final String url, int resId) {
        Glide.with(context).load(url)
                .apply(bitmapTransform(new CircleCrop())
                        .placeholder(resId)
                        .error(resId)
                        .fitCenter())
                .into(imageView);
    }

    public static void setImageBlur(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .apply(bitmapTransform(new BlurTransformation(25))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .fitCenter())
                .into(imageView);
    }

    public static void setImageBlur(Context context, ImageView imageView, @DrawableRes int resId) {
        Glide.with(context).load(resId)
                .apply(bitmapTransform(new BlurTransformation(25))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .fitCenter())
                .into(imageView);
    }

    public static void setImageBlurMult(Context context, ImageView imageView, String url) {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
        Glide.with(context).load(url)
                .apply(bitmapTransform(multi)
                        .fitCenter())
                .into(imageView);
    }

    public static void setImageBlurMult(Context context, ImageView imageView, @DrawableRes int resId) {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));
        Glide.with(context).load(resId)
                .apply(bitmapTransform(multi)
                        .fitCenter())
                .into(imageView);
    }

    public static void setImageGrayscale(Context context, ImageView imageView, String url) {
//        CropCircleTransformation              圆形剪裁显示      .bitmapTransform(new CircleCrop())
//        CropSquareTransformation              正方形剪裁        .bitmapTransform(new CropSquareTransformation())
//        RoundedCornersTransformation          圆角剪裁          .bitmapTransform(new RoundedCornersTransformation(this, 100, 0, RoundedCornersTransformation.CornerType.ALL))
//        CropTransformation                    自定义裁剪         .bitmapTransform(new CropTransformation(600, 200, CropTransformation.CropType.CENTER))
//        ColorFilterTransformation             颜色滤镜
//        ToonFilterTransformation              卡通滤波器         .bitmapTransform(new ToonFilterTransformation(this, 0.2F, 10F))
//        SepiaFilterTransformation             乌墨色滤波器       .bitmapTransform(new SepiaFilterTransformation(this, 1.0F))
//        ContrastFilterTransformation          对比度滤波器       .bitmapTransform(new ContrastFilterTransformation(this, 3F))
//        InvertFilterTransformation            反转滤波器         .bitmapTransform(new InvertFilterTransformation(this))
//        PixelationFilterTransformation        像素化滤波器(马赛克).bitmapTransform(new PixelationFilterTransformation(this, 20F))
//        SketchFilterTransformation            素描滤波器         .bitmapTransform(new SketchFilterTransformation(this))
//        SwirlFilterTransformation             旋转滤波器         .bitmapTransform(new SwirlFilterTransformation(this, 1.0F, 0.4F, new PointF(0.5F, 0.5F)))
//        BrightnessFilterTransformation        亮度滤波器         .bitmapTransform(new BrightnessFilterTransformation(this, 0.5F))
//        KuwaharaFilterTransformation          Kuwahara滤波器     .bitmapTransform(new KuwaharaFilterTransformation(this, 10))
//        VignetteFilterTransformation          装饰图滤波器       .bitmapTransform(new VignetteFilterTransformation(this, new PointF(0.5F, 0.5F), new float[]{0.0F, 0.0F, 0.0F}, 0.0F, 0.5F))
        Glide.with(context).load(url)
                //圆形裁剪
//                .apply(bitmapTransform(new CircleCrop()))
                //正方形裁剪
//                .apply(bitmapTransform(new CropSquareTransformation()))
//                //圆角剪裁
//                .apply(bitmapTransform(new RoundedCornersTransformation(100, 0, RoundedCornersTransformation.CornerType.ALL)))
//                //自定义裁剪
//                .apply(bitmapTransform(new CropTransformation(600, 200, CropTransformation.CropType.CENTER)))
//                //颜色滤镜
//                .apply(bitmapTransform(new ColorFilterTransformation(0x7900CCCC)))
//                //灰度
                .apply(bitmapTransform(new GrayscaleTransformation())
                        .fitCenter()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher))
//                //模糊
//                .apply(bitmapTransform(new BlurTransformation(25)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
//                //遮盖
//                .apply(bitmapTransform(new MaskTransformation(R.mipmap.ic_launcher)))
//                //卡通滤波器
//                .apply(bitmapTransform(new ToonFilterTransformation()))//bitmapTransform(new ToonFilterTransformation(0.2f,10f)
//                //乌墨色滤波器
//                .apply(bitmapTransform(new SepiaFilterTransformation()))//bitmapTransform(new SepiaFilterTransformation(1.0))
//                //对比度滤波器
//                .apply(bitmapTransform(new ContrastFilterTransformation(3f)))//bitmapTransform(new ContrastFilterTransformation(3f))
//                //反转滤波器
//                .apply(bitmapTransform(new InvertFilterTransformation()))
//                //像素化滤波器(马赛克)
//                .apply(bitmapTransform(new PixelationFilterTransformation()))
//                //素描滤波器
//                .apply(bitmapTransform(new SketchFilterTransformation()))
//                //旋转滤波器
//                .apply(bitmapTransform(new SwirlFilterTransformation()))
//                //亮度滤波器
//                .apply(bitmapTransform(new BrightnessFilterTransformation()))
//                //Kuwahara滤波器
//                .apply(bitmapTransform(new KuwaharaFilterTransformation()))
//                //装饰图滤波器
//                .apply(bitmapTransform(new VignetteFilterTransformation()))
                .into(imageView);
    }

}
