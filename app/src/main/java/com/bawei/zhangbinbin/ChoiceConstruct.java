package com.bawei.zhangbinbin;

import com.bawei.zhangbinbin.bean.MyBean;

/**
 * Created by Zhang on 2017/11/23.
 */

public interface ChoiceConstruct {
    interface IChoiceView{
        void ShowData(MyBean myBean);
        void ShowError(String e);
    }
    interface IChoiceModel{
        void OnRequestData(String url,OnChoiceListener onChoiceListener);
    }
    interface OnChoiceListener{
        void OnSuccess(MyBean myBean);
        void OnError(String e);
    }
    interface IChoicePresenter{
        void LoadData(String url);
    }
}
