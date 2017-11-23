package com.bawei.zhangbinbin.presenter;

import com.bawei.zhangbinbin.ChoiceConstruct;
import com.bawei.zhangbinbin.bean.MyBean;
import com.bawei.zhangbinbin.model.ChoiceModel;

/**
 * Created by Zhang on 2017/11/23.
 */

public class ChoicePresenter implements ChoiceConstruct.IChoicePresenter {
    ChoiceConstruct.IChoiceView   iChoiceView;
    ChoiceConstruct.IChoiceModel iChoiceModel;

    public ChoicePresenter(ChoiceConstruct.IChoiceView iChoiceView) {
        this.iChoiceView = iChoiceView;
        iChoiceModel = new ChoiceModel();
    }

    @Override
    public void LoadData(String url) {
        iChoiceModel.OnRequestData(url, new ChoiceConstruct.OnChoiceListener() {
            @Override
            public void OnSuccess(MyBean myBean) {
                iChoiceView.ShowData(myBean);
            }

            @Override
            public void OnError(String e) {
                iChoiceView.ShowError(e);
            }
        });
    }
}
