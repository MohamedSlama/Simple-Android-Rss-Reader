package com.rssuser;


import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class Scroll implements OnScrollListener{

	LayoutAnimationController controller1;
	Context context;
	public Scroll(Context context) {
		controller1  = AnimationUtils.loadLayoutAnimation(
				context, R.anim.list_layout_controller);
		this.context = context;
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
		view.setLayoutAnimation(controller1);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

}
