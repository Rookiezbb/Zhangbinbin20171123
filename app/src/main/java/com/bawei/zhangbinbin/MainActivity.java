package com.bawei.zhangbinbin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bawei.zhangbinbin.adapter.ChoiceAdapter;
import com.bawei.zhangbinbin.bean.MyBean;
import com.bawei.zhangbinbin.presenter.ChoicePresenter;
import com.bawei.zhangbinbin.utils.Api;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ChoiceConstruct.IChoiceView {

    @BindView(R.id.rcv)
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ChoicePresenter choicePresenter = new ChoicePresenter(this);
        choicePresenter.LoadData(Api.HOST);
    }

    @Override
    public void ShowData(MyBean myBean) {
        List<MyBean.RetBean.ListBean> list = myBean.getRet().getList();
        ChoiceAdapter choiceAdapter = new ChoiceAdapter(list, this);
        rcv.setAdapter(choiceAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void ShowError(String e) {
        Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        Log.e("哈哈哈哈哈哈哈哈", e);
    }
}
