package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.adapter.NavDrawerAdapter;
import praful.com.kidsonwheels.adapter.StudentsViewAdapter;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.model.Student;


public class HomeActivity extends BaseActivity {

    private View mEmptyView;

    @InjectView(R.id.students_list) RecyclerView mRecyclerView;

    @InjectView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.rvLeftDrawer) RecyclerView mLeftDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private String []mDrawerItemsList;
    private RecyclerView.Adapter mDrawerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private List<Student> mStudents;
    private StudentsViewAdapter mAdapter;

    private int []mIcons = {R.drawable.ic_action_add_student, R.drawable.ic_action_settings, R.drawable.ic_action_thumbs_up_down, R.drawable.ic_action_help};
    private String mUserName = "Praful Mantale";
    private int mProfileImageId = R.drawable.one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeNavigationDrawer();
    }

    private void initializeNavigationDrawer() {
        mDrawerItemsList = getResources().getStringArray(R.array.drawer_items_array);
        mLeftDrawer.setHasFixedSize(true);
        mDrawerAdapter = new NavDrawerAdapter(mDrawerItemsList, mIcons, mUserName, mProfileImageId);
        mLeftDrawer.setAdapter(mDrawerAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(HomeActivity.this, new GestureDetector.SimpleOnGestureListener(){
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mLeftDrawer.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && mGestureDetector.onTouchEvent(e)){
                    mDrawerLayout.closeDrawers();
                    int position = rv.getChildPosition(child);
                    if(position > 0 && position <= mDrawerItemsList.length) {
//                        selectItem(recyclerView.getChildPosition(child));
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mLeftDrawer.setLayoutManager(mLayoutManager);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    void initializeViews() {
        mStudents = DataManager.getInstance().getToBePickedStudents();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new StudentsViewAdapter(mStudents);
        mRecyclerView.setAdapter(mAdapter);
        updateEmptyViewState();
    }

    private void updateEmptyViewState() {
        if (mEmptyView == null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.empty_view);
            mEmptyView = viewStub.inflate();
        }
        if (mStudents == null || mStudents.isEmpty()) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.add_student_button)
    void onAddStudent(View view) {
        startActivity(new Intent(this, CreateStudentActivity.class));
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.slide_out_from_top);
    }
}
