package np.com.sagardevkota.daggertest.ui.views;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

import com.tealbox.app.R;


public class CustomProgressDialog extends AlertDialog {
	String message;
	TextView txtMessage;
	public CustomProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public CustomProgressDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		setContentView(R.layout.dialog_progress);
		txtMessage=(TextView) findViewById(
				R.id.text_view_progress_message);
		
	}
	
	public CustomProgressDialog message(String message){
		this.message=message;
		return this;
	}
	
	public void setMessage(String message){
		this.message=message;
		txtMessage.setText(this.message);
		//return this;
	}
	
	
}
