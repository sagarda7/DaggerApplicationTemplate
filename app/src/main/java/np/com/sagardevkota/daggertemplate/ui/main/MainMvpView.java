package np.com.sagardevkota.daggertemplate.ui.main;

import np.com.sagardevkota.daggertemplate.ui.base.MvpView;

/**
 * Created by Dell on 10/18/2016.
 * All activity UI update functions here which will be called back to Related Activity
 */
public interface MainMvpView extends MvpView {
    void showName(String name);
    void showGithubNames(String name);
}
