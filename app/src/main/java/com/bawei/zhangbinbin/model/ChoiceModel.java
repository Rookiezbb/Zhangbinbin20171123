package com.bawei.zhangbinbin.model;

import com.bawei.zhangbinbin.ChoiceConstruct;
import com.bawei.zhangbinbin.bean.MyBean;
import com.bawei.zhangbinbin.utils.Api;
import com.bawei.zhangbinbin.utils.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/23.
 */

public class ChoiceModel implements ChoiceConstruct.IChoiceModel{

    @Override
    public void OnRequestData(String url, final ChoiceConstruct.OnChoiceListener onChoiceListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<MyBean> datas = apiService.getDatas();
        datas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onChoiceListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        onChoiceListener.OnSuccess(myBean);
                    }
                });
    }

}
