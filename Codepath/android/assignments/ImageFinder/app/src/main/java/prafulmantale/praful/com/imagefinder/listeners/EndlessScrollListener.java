package prafulmantale.praful.com.imagefinder.listeners;

import android.widget.AbsListView;

/**
 * Created by prafulmantale on 9/24/14.
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount;
    private boolean loading = false;
    private int startingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startingPageIndex) {
        this.startingPageIndex = startingPageIndex;
        this.visibleThreshold = visibleThreshold;
        this.currentPage = startingPageIndex;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if(totalItemCount < previousTotalItemCount){
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;

            if(totalItemCount == 0){
                this.loading = true;
            }
        }

        if(loading && (totalItemCount > previousTotalItemCount)){
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage ++;
        }

        if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
            onLoadMore(currentPage + 1, totalItemCount);
        }
    }

    public abstract void onLoadMore(int page, int totalCount);
}
