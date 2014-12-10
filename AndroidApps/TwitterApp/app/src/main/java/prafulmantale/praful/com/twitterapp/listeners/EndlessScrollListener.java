package prafulmantale.praful.com.twitterapp.listeners;

import android.widget.AbsListView;

/**
 * Created by prafulmantale on 9/29/14.
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 8;
    private int currentPage = 0;
    private int previousItemTotalCount = 0;
    private boolean loading = false;
    private int startingPageIndex = 0;


    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleThreshold){
        this.visibleThreshold = visibleThreshold;
    }


    protected EndlessScrollListener(int visibleThreshold, int startingPageIndex) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startingPageIndex;
        this.currentPage = startingPageIndex;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if(totalItemCount < previousItemTotalCount){
            this.currentPage = this.startingPageIndex;
            this.previousItemTotalCount = totalItemCount;

            if(totalItemCount == 0){
                this.loading = true;
            }
        }

        if(loading && (totalItemCount > previousItemTotalCount)){
            loading = false;
            previousItemTotalCount = totalItemCount;
            currentPage ++;
        }

        if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
            onLoadMore(currentPage + 1, totalItemCount);
        }
    }

    public abstract void onLoadMore(int page, int totalCount);
}
