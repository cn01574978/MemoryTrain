package com.xyys.memorytext.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xyys.memorytext.Engine.UserEngine;
import com.xyys.memorytext.R;
import com.xyys.memorytext.bean.User;
import com.xyys.memorytext.constant.ConstantValue;
import com.xyys.memorytext.constant.GlobalParams;
import com.xyys.memorytext.net.xmlProtocal.Message;
import com.xyys.memorytext.net.xmlProtocal.Oelement;
import com.xyys.memorytext.net.xmlProtocal.element.BalanceElement;
import com.xyys.memorytext.util.BeanFactory;
import com.xyys.memorytext.util.PromptManager;
import com.xyys.memorytext.view.manager.BaseUI;
import com.xyys.memorytext.view.manager.MiddleManager;

//import com.ithm.lotteryhm28.ConstantValue;
//import com.ithm.lotteryhm28.GlobalParams;
//import com.ithm.lotteryhm28.R;
//import com.ithm.lotteryhm28.bean.User;
//import com.ithm.lotteryhm28.engine.UserEngine;
//import com.ithm.lotteryhm28.net.protocal.Message;
//import com.ithm.lotteryhm28.net.protocal.Oelement;
//import com.ithm.lotteryhm28.net.protocal.element.BalanceElement;
//import com.ithm.lotteryhm28.util.BeanFactory;
//import com.ithm.lotteryhm28.util.PromptManager;
//import com.ithm.lotteryhm28.view.manager.BaseUI;
//import com.ithm.lotteryhm28.view.manager.MiddleManager;

/**
 * 用户登陆+余额获取 两个登录入口：主动登录（购彩大厅）；被动登录（购物车）
 */
public class UserLogin extends BaseUI {

	private EditText username;
	private ImageView clear;// 清空用户名
	private EditText password;
	private Button login;

	public UserLogin(Context context) {
		super(context);
	}

	@Override
	public void init() {
		showInMiddle = (ViewGroup) View.inflate(context, R.layout.il_user_login, null);

		username = (EditText) findViewById(R.id.ii_user_login_username);
		clear = (ImageView) findViewById(R.id.ii_clear);
		password = (EditText) findViewById(R.id.ii_user_login_password);
		login = (Button) findViewById(R.id.ii_user_login);

	}

	@Override
	public void setListener() {
		username.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (username.getText().toString().length() > 0) {
					clear.setVisibility(View.VISIBLE);
				}

			}
		});

		clear.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public int getID() {
		return ConstantValue.VIEW_LOGIN;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ii_clear:
			// 清除用户名
			username.setText("");
			clear.setVisibility(View.INVISIBLE);
			break;
		case R.id.ii_user_login:
			// 登录
			// 用户输入信息
			if (checkUserInfo()) {
				// 登录
				User user = new User();
				user.setUsername(username.getText().toString());
				user.setPassword(password.getText().toString());
				new MyHttpTask<User>() {
					@Override
					protected void onPreExecute() {
						PromptManager.showProgressDialog(context);
						super.onPreExecute();
					}

					@Override
					protected Message doInBackground(User... params) {
						UserEngine engine = BeanFactory.getImpl(UserEngine.class);
						Message login = engine.login(params[0]);

						if (login != null) {
							Oelement oelement = login.getBody().getOelement();
							if (ConstantValue.SUCCESS.equals(oelement.getErrorcode())) {
								// 登录成功了
								GlobalParams.isLogin = true;
								GlobalParams.USERNAME=params[0].getUsername();

								// 成功了获取余额
								Message balance = engine.getBalance(params[0]);
								if (balance != null) {
									oelement = balance.getBody().getOelement();
									if (ConstantValue.SUCCESS.equals(oelement.getErrorcode())) {
										BalanceElement element = (BalanceElement) balance.getBody().getElements().get(0);
										GlobalParams.MONEY = Float.parseFloat(element.getInvestvalues());
										return balance;
									}
								}
							}
						}

						return null;
					}

					@Override
					protected void onPostExecute(Message result) {
						PromptManager.closeProgressDialog();
						if (result != null) {
							// 界面跳转
							PromptManager.showToast(context, "登录成功");
							MiddleManager.getInstance().goBack();
						} else {
							PromptManager.showToast(context, "服务忙……");
						}
						super.onPostExecute(result);
					}
				}.executeProxy(user);

			}
			break;
		}
	}

	/**
	 * 用户信息判断
	 * 
	 * @return
	 */
	private boolean checkUserInfo() {

		return true;
	}

}
