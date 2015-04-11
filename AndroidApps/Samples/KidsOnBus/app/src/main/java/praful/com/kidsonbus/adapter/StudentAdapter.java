package praful.com.kidsonbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import praful.com.kidsonbus.R;
import praful.com.kidsonbus.model.Student;

/**
 * Created by prafulmantale on 4/11/15.
 */
public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.students_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.populateData(student);

        return convertView;
    }


    private class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView distance;
        TextView address;

        ViewHolder(View convertView) {
            avatar = (ImageView)convertView.findViewById(R.id.student_avatar);
            name = (TextView)convertView.findViewById(R.id.student_name);
            distance = (TextView)convertView.findViewById(R.id.student_distance);
            address = (TextView)convertView.findViewById(R.id.student_address);
        }

        protected void populateData(Student student) {
            if (student == null) {
                return;
            }
            name.setText(student.getDisplayName());
            address.setText(student.getDisplayAddress());

            if(student.getAvatarId() != 0){
                Picasso.with(getContext()).load(student.getAvatarId()).into(avatar);
            }
        }
    }
}
