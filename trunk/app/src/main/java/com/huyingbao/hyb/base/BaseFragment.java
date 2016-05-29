package com.huyingbao.hyb.base;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
//    public static final String TAG = BaseFragment.class.getSimpleName();
//    protected View mRootView;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mRootView = inflater.inflate(getLayoutId(), container, false);
//        return mRootView;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//        afterCreate(savedInstanceState);
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    /**
     * 得到上下文
     *
     * @return
     */
    public Context getContext() {
        return getActivity();
    }

//    protected abstract int getLayoutId();
//
//    protected abstract void afterCreate(Bundle savedInstanceState);

}
