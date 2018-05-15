package com.example.gll.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.HomeAdapter;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.loader.GlideImageLoader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 首页页面
 */

public class HomeFragment extends BaseFragment implements OnRefreshListener,OnLoadMoreListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private ArrayList<String> mList=new ArrayList<>();;
    private HomeAdapter adapter;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        setBanner();
        initRefresh();
    }
    private void initData(){
//        RequestCenter.postRequest();
    }

    private void initRefresh() {
        adapter=new HomeAdapter( getData() );
        recyclerView.setAdapter(adapter);
//        ScrollLinearLayoutManager scrollLinearLayoutManager=new ScrollLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setFocusable(false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.setEnableLoadMore(false);
//        refreshLayout.setOnLoadMoreListener(this);
    }

    private void setBanner() {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> list = new ArrayList<>();
        //网络图片
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
        banner.setImages(list);
        //设置轮播时间
        banner.setDelayTime(1500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            banner.stopAutoPlay();
        }else {
            banner.startAutoPlay();
        }
    }

    private ArrayList<String> getData(){
        mList=new ArrayList<>();
        mList.add("热门影片");
        mList.add("最新综艺");
        mList.add("演员选拔");
        mList.add("电影众筹");
        return mList;
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        adapter.updateData(getData());
        refreshLayout.finishLoadMore(1100/*,false*/);//传入false表示加载失败
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1100/*,false*/);//传入false表示刷新失败
    }
}