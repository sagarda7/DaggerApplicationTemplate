package np.com.sagardevkota.daggertemplate.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tealbox.app.R;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.sagardevkota.daggertemplate.ui.base.BaseFragment;
import np.com.sagardevkota.daggertemplate.ui.main.presenters.HomeFragmentPresenter;
import np.com.sagardevkota.daggertemplate.ui.main.views.HomeFragmentMvpView;

/**
 * Created by Dell on 12/20/2016.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentMvpView {
    @Inject
    HomeFragmentPresenter mPresenter;

    @BindView(R.id.text_fragment_main_home_data)
    TextView txtData;

    @Inject
    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getFragmentComponent().inject(this);
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mPresenter.loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showData(String s) {
        txtData.setText(s);
    }


}
