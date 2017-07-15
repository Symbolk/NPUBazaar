package com.way.tabui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;

/**
 * 主界面框架
 * 
 * 注意继承自FragmentActivity，才会有getSupportFragmentManager()函数
 * 
 * @author way
 * 
 */
public class MainActivity extends FragmentActivity implements
		OnPageChangeListener {
	private SlidingMenu menu; 
	private static final int NUM_PAGES = 2;// 总页数，2个Fragment
	public static final int PAGE_PERSONAL = 0;// 第一个界面ID
	public static final int PAGE_FILE_SYSTEM = 1;// 第二个界面ID
	private static final int ROTATE_ANIM_DURATION = 300;// 左下角切换动画的时间

	private int mCurPage = 0;// 当前页
	private ViewPager mViewPager;// 父容器由一个ViewPager实现
	private PagerAdapter mPagerAdapter;// ViewPager适配器
	private ImageButton mSwitchImageButton;// 左下角切换Paper按钮
	private ImageView mAnimView;// 动画View
	private Animation mRotateRightAnim;// 向右旋转动画
	private Animation mRotateLeftAnim;// 向左旋转动画

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 menu=new SlidingMenu(this);
		 menu.setMode(SlidingMenu.LEFT);
	        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	      
	        menu.setBehindWidth(450);
	        menu.setFadeDegree(0.35f);
	        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	        menu.setMenu(R.layout.layout_menu);
	        TextView about=(TextView)findViewById(R.id.about);
	        about.setOnClickListener(new View.OnClickListener(){
	        
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(MainActivity.this,AboutActivity.class);
					//intent.setClass(MainActivity.this,Publish.class);
  				MainActivity.this.startActivity(intent);
					
				}
	        });
	        TextView search=(TextView)findViewById(R.id.search);
	        search.setOnClickListener(new View.OnClickListener(){
	        
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(MainActivity.this,SearchActivity.class);
					//intent.setClass(MainActivity.this,Publish.class);
  				MainActivity.this.startActivity(intent);
					
				}
	        });
	        TextView publish=(TextView)findViewById(R.id.publish);
	        publish.setOnClickListener(new View.OnClickListener(){
	        
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Toast.makeText(getApplicationContext(), "此功能需登录后使用，谢谢！",Toast.LENGTH_SHORT).show();
					
				}
	        });
	        TextView exit=(TextView)findViewById(R.id.exit);
	        exit.setOnClickListener(new View.OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
	        		builder.setIcon(R.drawable.alert_dialog_icon);
	        		builder.setTitle("确定退出？");
	        		builder.setMessage("狠心离开吗?");
	        		builder.setPositiveButton("Yes",
	        				new DialogInterface.OnClickListener() 
	        			{
	        					public void onClick(DialogInterface dialog, int whichButton) 
	        					{
	        						finish();
	        					}
		        		});
	        		
	        		builder.setNegativeButton("No",
	        				new DialogInterface.OnClickListener() 
	        				{
	        					public void onClick(DialogInterface dialog, int whichButton)
	        					{
	        						
	        					}
	        				});
	        		builder.show();
	        		
	        	}
	        	
	        });
		initView();
		initAnim();
	}

	/**
	 * 初始化Views
	 */
	private void initView() {
		mAnimView = (ImageView) findViewById(R.id.anim_icon);
		mSwitchImageButton = (ImageButton) findViewById(R.id.switch_btn);

		mViewPager = (ViewPager) findViewById(R.id.vp_pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mPagerAdapter);
		mCurPage = PAGE_FILE_SYSTEM;
		mViewPager.setCurrentItem(mCurPage);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
	}

	/**
	 * 初始化动画
	 */
	private void initAnim() {
		mRotateRightAnim = new RotateAnimation(0.0f, 180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateRightAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateRightAnim.setFillAfter(true);
		mRotateLeftAnim = new RotateAnimation(180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateLeftAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateLeftAnim.setFillAfter(true);
		mRotateRightAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mAnimView.clearAnimation();
				mAnimView.setVisibility(View.GONE);
				mSwitchImageButton.setVisibility(View.VISIBLE);
				mSwitchImageButton
						.setImageResource(R.drawable.ic_viewpager_switch_feedlist);
			}
		});
		mRotateLeftAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mAnimView.clearAnimation();
				mAnimView.setVisibility(View.GONE);
				mSwitchImageButton.setVisibility(View.VISIBLE);
				mSwitchImageButton
						.setImageResource(R.drawable.ic_viewpager_switch_filesystem);
			}
		});
	}

	/**
	 * 点击左下角切换按钮的事件处理
	 * 
	 * 需要事先在布局中声明 android:onClick="switchPage"
	 * 
	 * @param view
	 */
	public void switchPage(View view) {
		if (mCurPage == PAGE_FILE_SYSTEM) {
			mViewPager.setCurrentItem(PAGE_PERSONAL);
		} else if (mCurPage == PAGE_PERSONAL) {
			mViewPager.setCurrentItem(PAGE_FILE_SYSTEM);
		}
	}

	/**
	 * 开始动画
	 * 
	 * @param pager
	 *            当前页
	 */
	private void startAmin(int pager) {
		if (pager == PAGE_FILE_SYSTEM) {
			mSwitchImageButton.setVisibility(View.INVISIBLE);
			mAnimView.setVisibility(View.VISIBLE);
			mAnimView.startAnimation(mRotateLeftAnim);
		} else if (pager == PAGE_PERSONAL) {
			mSwitchImageButton.setVisibility(View.INVISIBLE);
			mAnimView.setVisibility(View.VISIBLE);
			mAnimView.startAnimation(mRotateRightAnim);
		}
		mCurPage = pager;
	}

	/**
	 * ViewPager的适配器，我这里只是一个例子，就作为内部类了。
	 * 
	 * @author way
	 * 
	 */
	private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * 这里可以将Fragment缓存一下，减少加载次数，提高用户体验，我未作处理
		 */
		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case PAGE_PERSONAL:
				return new MarketFragment();
			case PAGE_FILE_SYSTEM:
				return new BazaarFragment();
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		startAmin(arg0);//手势滑动ViewPager时，也要播放一下动画
	}
}
