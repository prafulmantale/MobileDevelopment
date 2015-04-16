package praful.com.kidsonwheels.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import praful.com.kidsonwheels.R;
import praful.com.kidsonwheels.model.Student;

/**
 * Created by prafulmantale on 4/14/15.
 */
public class StudentsViewAdapter extends RecyclerView.Adapter<StudentsViewAdapter.StudentViewHolder> {

    public class ViewTypes {
        public static final int Header = 1;
        public static final int Normal = 2;

    }

    private List<Student> mStudents;

    public StudentsViewAdapter(List<Student> students) {
        mStudents = students;
    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView avatar;
        TextView name;
        TextView distance;
        TextView address;
        TextView state;
        View divider;

        StudentViewHolder(View itemView)

        {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.student_name);
            distance = (TextView) itemView.findViewById(R.id.student_distance);
            address = (TextView) itemView.findViewById(R.id.student_address);
            avatar = (ImageView) itemView.findViewById(R.id.student_avatar);
            state = (TextView) itemView.findViewById(R.id.student_pickup_state);
            divider = itemView.findViewById(R.id.end_divider);
        }

    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (mStudents.get(position).isHeader())
            return ViewTypes.Header;
        else
            return ViewTypes.Normal;

    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        switch (i) {
            case ViewTypes.Normal:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.students_list_item, viewGroup, false);
                break;
            case ViewTypes.Header:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_list_item, viewGroup, false);
                break;

            default:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.students_list_item, viewGroup, false);
                break;
        }


        StudentViewHolder pvh = new StudentViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder viewHolder, int i) {
        if (!mStudents.get(i).isHeader()) {
            viewHolder.name.setText(mStudents.get(i).getDisplayName());
            viewHolder.address.setText(mStudents.get(i).getDisplayAddress());
            viewHolder.avatar.setImageResource(mStudents.get(i).getAvatarId());
            viewHolder.distance.setText(mStudents.get(i).getDistance());
            viewHolder.state.setText(mStudents.get(i).getPickupState().getValue());
            if (mStudents.get(i).getPickupState() != Student.PickupState.NONE) {
                viewHolder.divider.setVisibility(View.VISIBLE);
                viewHolder.state.setVisibility(View.VISIBLE);
            } else {
                viewHolder.divider.setVisibility(View.GONE);
                viewHolder.state.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
