package np.com.sagardevkota.daggertemplate.ui.base;

import np.com.sagardevkota.daggertemplate.ui.main.views.MainMvpView;

import javax.inject.Inject;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    /**
     * Created by Dell on 10/18/2016.
     */
    class MainPresenter extends BasePresenter<MainMvpView> {
        private String TAG = getClass().getName();

        @Inject
        public MainPresenter() {
        }

        @Override
        public void attachView(MainMvpView mvpView) {
            super.attachView(mvpView);
        }

        @Override
        public void detachView() {
            super.detachView();
        }
    }
}
