package com.xyys.memorytext.view.manager;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyys.memorytext.R;
import com.xyys.memorytext.constant.ConstantValue;
import com.xyys.memorytext.constant.GlobalParams;
import com.xyys.memorytext.view.UserLogin;

import org.apache.commons.lang3.StringUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * 管理标题容器的工具
 * 
 * @author Administrator
 * 
 */
public class TitleManager implements Observer {
	// 显示和隐藏

	private static TitleManager instance = new TitleManager();

	private TitleManager() {
	}

	public static TitleManager getInstance() {
		return instance;
	}

	private RelativeLayout commonContainer;
	private RelativeLayout loginContainer;
	private RelativeLayout unLoginContainer;

	private ImageView goback;		// 返回
	private ImageView help;			// 帮助
	private ImageView login;		// 登录

	private TextView titleContent;	// 标题内容
	private TextView userInfo;		// 用户信息

	public void init(Activity activity) {
		commonContainer = (RelativeLayout) activity.findViewById(R.id.ii_common_container);
		unLoginContainer = (RelativeLayout) activity.findViewById(R.id.ii_unlogin_title);
		loginContainer = (RelativeLayout) activity.findViewById(R.id.ii_login_title);

		goback = (ImageView) activity.findViewById(R.id.ii_title_goback);
		help = (ImageView) activity.findViewById(R.id.ii_title_help);
		login = (ImageView) activity.findViewById(R.id.ii_title_login);

		titleContent = (TextView) activity.findViewById(R.id.ii_title_content);
		userInfo = (TextView) activity.findViewById(R.id.ii_top_user_info);

		setListener();
	}

	private void setListener() {
		goback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("返回键");

			}
		});
		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("help");

			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("login");

				// SecondUI secondUI = new
				// SecondUI(MiddleManager.getInstance().getContext());
				MiddleManager.getInstance().changeUI(UserLogin.class);// changeUI需要修改，不能传递对象，但是明确目标
			}
		});

	}

	private void initTitle() {
		commonContainer.setVisibility(View.GONE);
		loginContainer.setVisibility(View.GONE);
		unLoginContainer.setVisibility(View.GONE);
	}

	/**
	 * 显示通用标题
	 */
	public void showCommonTitle() {
		initTitle();
		commonContainer.setVisibility(View.VISIBLE);
	}

	/**
	 * 显示未登录的标题
	 */
	public void showUnLoginTitle() {
		initTitle();
		unLoginContainer.setVisibility(View.VISIBLE);
	}

	/**
	 * 显示登陆的标题
	 */
	public void showLoginTitle() {
		initTitle();
		loginContainer.setVisibility(View.VISIBLE);

	}

	public void changeTitle(String title) {
		titleContent.setText(title);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case ConstantValue.VIEW_FIRST:
				showUnLoginTitle();
				break;
			case ConstantValue.VIEW_SECOND:
			case ConstantValue.VIEW_SSQ:
			case ConstantValue.VIEW_SHOPPING:
			case ConstantValue.VIEW_LOGIN:
			case ConstantValue.VIEW_PREBET:
				showCommonTitle();
				break;

			case ConstantValue.VIEW_HALL:
				if (GlobalParams.isLogin) {
					showLoginTitle();
					String info = "用户名：" + GlobalParams.USERNAME + "\r\n" + "余额:" + GlobalParams.MONEY;
					userInfo.setText(info);
				} else {
					showUnLoginTitle();
				}
				break;
			}
		}

	}
}
