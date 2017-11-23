package com.bawei.zhangbinbin.utils;

import com.bawei.zhangbinbin.bean.MyBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Zhang on 2017/11/23.
 */

public interface ApiService {
    @GET("homePageApi/homePage.do")
    Observable<MyBean> getDatas();
}
