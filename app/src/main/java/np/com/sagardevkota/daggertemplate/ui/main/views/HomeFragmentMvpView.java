package np.com.sagardevkota.daggertemplate.ui.main.views;



import java.util.List;

import np.com.sagardevkota.daggertemplate.ui.base.MvpView;

/**
 * Created by Dell on 10/18/2016.
 * All activity UI update functions here which will be called back to Related Activity
 */
public interface HomeFragmentMvpView extends MvpView {
    void showData(String s);
}
