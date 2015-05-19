package praful.com.kidsonwheels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.adapter.StudentsViewAdapter;
import praful.com.kidsonwheels.manager.DataManager;
import praful.com.kidsonwheels.model.Student;


public class HomeActivity extends BaseActivity {

    private View mEmptyView;

    @InjectView(R.id.students_list) RecyclerView mRecyclerView;
    private List<Student> mStudents;
    private StudentsViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
