package com.mrdo.example.appframework;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mrdo.example.appframework.base.BasePresenter;
import com.mrdo.example.appframework.base.BaseThemeActivity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by dulijie on 2018/9/13.
 */
public class TestActivity extends BaseThemeActivity {
    public static final String TAG = "RX_Test";
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
//    @BindView(R.id.tv_content)
//    TextView textView;
    @BindView(R.id.namess)
    TextView name1;
    @BindView(R.id.pwdss)
    TextView pwd1;

    PublishSubject<String> mNameSubject, mPasswordSubject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                mDrawerLayout.setClipToPadding(false);
            }
        }
//        all();
//        join();
//        zipWith();
//        mNameSubject = PublishSubject.create();
//        mPasswordSubject = PublishSubject.create();
//        name1.addTextChangedListener(new EditTextMonitor(mNameSubject));
//        pwd1.addTextChangedListener(new EditTextMonitor(mPasswordSubject));
//        Observable<Boolean> obc = Observable.combineLatest(mNameSubject, mPasswordSubject, new BiFunction<String, String, Boolean>() {
//            @Override
//            public Boolean apply(String s, String s2) throws Exception {
//                int namelength = s.length();
//                int pwdlength = s2.length();
//                return namelength >= 2 && namelength <= 8 && pwdlength >= 4 && pwdlength <= 10;
//            }
//        });
//
//
//        DisposableObserver<Boolean> disposable = new DisposableObserver<Boolean>() {
//            @Override
//            public void onNext(Boolean aBoolean) {
//                textView.setText(aBoolean ? "登录" : "用户名或密码无效");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        obc.subscribe(disposable);
//        withLastFrom();
//        switchMap();

//        amb();
//        contains();
//        switchIfEmpty();
//        skipUtil();
        skipWhile();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void zipWith() {
        Observable<Long> observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob1-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());
        Observable<Long> observable2 = Observable.interval(100,200, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob2-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());
//                   0，1，0，2，1，2

//        Observable.zip(observable1, observable2, new BiFunction<Long, Long, Long>() {
//            @Override
//            public Long apply(Long aLong, Long aLong2) throws Exception {
//                LogUtils.d(TAG,"zip->>"+aLong+"->"+aLong2);
//                return aLong + aLong2;
//            }
//        }).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                LogUtils.d(TAG, "zip->>" + aLong);
//            }
//        });


//        observable1.zipWith(observable2, new BiFunction<Long, Long, Long>() {
//            @Override
//            public Long apply(Long aLong, Long aLong2) throws Exception {
//                LogUtils.d(TAG,"zipWith->>"+aLong+"->"+aLong2);
//                return aLong+aLong2;
//            }
//        }).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                LogUtils.d(TAG, "zipWith->>" + aLong);
//            }
//        });


        Observable.merge(observable1, observable2)
                .subscribe(o -> Log.d(TAG, "merge->" + o));

    }

    class EditTextMonitor implements TextWatcher {
        private PublishSubject<String> mPublishSubject;

        EditTextMonitor(PublishSubject<String> publishSubject) {
            mPublishSubject = publishSubject;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mPublishSubject.onNext(s.toString());
        }
    }

    private void withLastFrom() {
        Observable<Long> observable2 = Observable.interval(150, TimeUnit.MILLISECONDS)
                .take(4)
                .subscribeOn(Schedulers.newThread());
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .subscribeOn(Schedulers.newThread())
                .withLatestFrom(observable2, (aLong, aLong2) -> {
                    System.out.print("aLong:" + aLong + "\t aLong2:" + aLong2 + "\t");
                    return aLong + aLong2;
                })
                .subscribe(o -> System.out.println("===>" + o + "\t"));
    }

    private void switchMap() {
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(3)
                .doOnNext(aLong -> System.out.println("old->" + aLong))
                .switchMap(aLong -> Observable.intervalRange(aLong * 10, 3,
                        0, 300, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.newThread()))
                .subscribe(aLong -> System.out.println("new->" + aLong + " "));
    }

    private void join() {
        Observable<Long> observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob1-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());
        Observable<Long> observable2 = Observable.interval(200, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob2-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());

        Observable.intervalRange(10, 4, 0, 300, TimeUnit.MILLISECONDS)
                .join(Observable.interval(100, TimeUnit.MILLISECONDS)
                                .take(7)
                        , new Function<Long, ObservableSource<Long>>() {
                            @Override
                            public ObservableSource<Long> apply(Long aLong) throws Exception {
                                return Observable.just(aLong);
                            }
                        }
                        , new Function<Long, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(Long aLong) throws Exception {
                                return Observable.just("fun=>" + aLong);
                            }
                        }
                        , new BiFunction<Long, Long, String>() {
                            @Override
                            public String apply(Long aLong, Long aLong2) throws Exception {
                                return "==" + aLong + "==" + aLong2 + "==";
                            }
                        }
                )
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(TAG + "->>" + s);
                    }
                });

    }

    private void all() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .all(a -> a > 3)
                .subscribe((ab, th) -> System.out.println(TAG + "==>>" + ab));
    }

    private void amb() {
        Observable<Long> ob1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob1-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());
        Observable<Long> ob2 = Observable.interval(200, TimeUnit.MILLISECONDS)
                .take(3)
                .map(aLong -> {
                    Log.d(TAG, "ob2-->" + aLong);
                    return aLong;
//                    0，1，2
                })
                .subscribeOn(Schedulers.newThread());
        Observable.amb(new Iterable<ObservableSource<Long>>() {
            @NonNull
            @Override
            public Iterator<ObservableSource<Long>> iterator() {
                Set<ObservableSource<Long>> set = new HashSet<>();
                set.add(ob1);
                set.add(ob2);
                return set.iterator();
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println(TAG+"=doOnComplete=");
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println(TAG+"=subscribe="+aLong);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(TAG+"=Throwable="+throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println(TAG+"=Action=");
            }
        });

    }

    private void contains(){
        Observable.just(1,2,3,4)
                .contains(2)
                .subscribe(t-> System.out.println(t));
    }

    private void switchIfEmpty(){
        Observable.empty()
                .switchIfEmpty(Observable.just(2,3,4))
                .subscribe(o->{System.out.println(TAG+"->>"+o);});
    }

    private void skipUtil(){
        System.out.println("Start=");
        Observable.intervalRange(30, 20, 500, 100, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(1000, TimeUnit.MILLISECONDS))
                .doOnNext(integer -> System.out.println(integer))
                //此时用这个主要是 测试环境 有执行时间 所以用阻塞比较好
                .blockingSubscribe();
    }

    private void skipWhile(){
        Observable.just(1,2,1,4)
                //从2开始 因为2条件不成立
                .skipWhile(aLong -> aLong==1)
                .doOnNext(integer -> System.out.println(integer))
                //此时用这个主要是 测试环境 有执行时间 所以用阻塞比较好
                .blockingSubscribe();
    }
}
