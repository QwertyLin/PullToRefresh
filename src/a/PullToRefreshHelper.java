package a;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PullToRefreshHelper {
	
	public interface OnPullToRefreshListener {
		void onRefresh();
	}
	
	private PullToRefreshListView mPullToRefreshView;
	
	public PullToRefreshHelper(final Context ctx, FrameLayout parentView, final OnPullToRefreshListener listener){
		mPullToRefreshView = new PullToRefreshListView(ctx);
		mPullToRefreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				mPullToRefreshView.setLastUpdatedLabel(DateUtils.formatDateTime(ctx,
						System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL));
				listener.onRefresh();
			}
		});
		//
		parentView.addView(mPullToRefreshView);
	}
	
	public ListView getListView(){
		return mPullToRefreshView.getRefreshableView();
	}

}
