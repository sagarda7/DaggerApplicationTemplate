package np.com.sagardevkota.daggertemplate.ui.login;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tealbox.app.R;

import np.com.sagardevkota.daggertemplate.ui.base.BaseActivity;
import np.com.sagardevkota.daggertemplate.ui.main.MainActivity;
import np.com.sagardevkota.daggertemplate.ui.views.CustomProgressDialog;
import np.com.sagardevkota.daggertemplate.utils.formvalidator.FormValidator;
import np.com.sagardevkota.daggertemplate.utils.formvalidator.ValidationRule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginPresenter mPresenter;

    @Inject
    FormValidator validator;

    @Inject
    CustomProgressDialog progress;

    @BindView(R.id.txt_act_login_email)
    EditText email;

    @BindView(R.id.txt_act_login_password)
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.attachView(this);
    }

    @Override
    public void onLoginSuccess() {
        progress.hide();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onLoginError() {
        progress.hide();
        showResponse("Login Error, Credentials wrong");
    }

    @Override
    public void onNetworkError() {
        showResponse("Network Error, Please check connection.");
        progress.hide();
    }

    @OnClick(R.id.btn_act_login_login) void loginClicked(View v){
        boolean valid=validator
                .add(
                    new ValidationRule(email)
                    .add(ValidationRule.EMAIL,"Invalid Email")
                )
                .add(new ValidationRule(password)
                .add(ValidationRule.REQUIRED,"Password is required")
                ).validate();

        if(!valid) return;
        progress.show();
        mPresenter.checkLogin(email.getText().toString(),password.getText().toString());

    }

    void showResponse(String message){
        Snackbar mSnackbar=Snackbar.make(password,message,Snackbar.LENGTH_LONG);
        // get snackbar view
        View mView = mSnackbar.getView();
        // get textview inside snackbar view
        TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        // set text to center
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            mTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        mSnackbar.show();
    }
}
