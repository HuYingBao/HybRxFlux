//package com.huyingbao.hyb.ui.home;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.huyingbao.hyb.R;
//import com.huyingbao.hyb.HybApp;
//import com.huyingbao.hyb.base.BaseFragment;
//import com.huyingbao.hyb.model.GitUser;
//import com.huyingbao.hyb.stores.UsersStore;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class UserFragment extends BaseFragment {
//    private static final String ARG_USER_ID = "userId";
//    @Bind(R.id.user_name)
//    TextView userNameView;
//    @Bind(R.id.login)
//    TextView loginView;
//    @Bind(R.id.statistics)
//    TextView statsView;
//    private String userId;
//
//    public UserFragment() {
//        // Required empty public constructor
//    }
//
//    public static UserFragment newInstance(String userId) {
//        UserFragment fragment = new UserFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_USER_ID, userId);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            userId = getArguments().getString(ARG_USER_ID);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_user, container, false);
//
//        ButterKnife.bind(this, view);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        GitUser user = UsersStore.get(HybApp.get(getActivity()).getRxFlux().getDispatcher()).getUser(userId);
//        userNameView.setText(user.getName());
//        loginView.setText(user.getLogin());
//        statsView.setText(getStats(user));
//    }
//
//    private String getStats(GitUser user) {
//        StringBuilder stats = new StringBuilder();
//        stats.append("Public repos: ").append(user.getPublicRepos()).append("\n");
//        stats.append("Public gists: ").append(user.getPublicGists()).append("\n");
//        stats.append("Followers: ").append(user.getFollowers());
//        return stats.toString();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//}
