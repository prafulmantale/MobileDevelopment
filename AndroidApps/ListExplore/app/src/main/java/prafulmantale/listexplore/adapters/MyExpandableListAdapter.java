package prafulmantale.listexplore.adapters;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import prafulmantale.listexplore.R;
import prafulmantale.listexplore.models.Group;

/**
 * Created by prafulmantale on 8/15/14.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> sparseArray;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public MyExpandableListAdapter(Activity activity, SparseArray<Group> sparseArray) {
        this.activity = activity;
        this.sparseArray = sparseArray;
        layoutInflater = activity.getLayoutInflater();
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sparseArray.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String child =  (String)getChild(groupPosition, childPosition);

        TextView view = null;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listrow_details, null);
        }

        view = (TextView)convertView.findViewById(R.id.textviewrow);
        view.setText(child);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, child, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return sparseArray.get(groupPosition).getChildren().size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return sparseArray.get(groupPosition);
    }



    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public int getGroupCount() {
        return sparseArray.size();
    }



    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listrow_group, null);
        }

        Group group = (Group)getGroup(groupPosition);

        CheckedTextView checkedTextView = (CheckedTextView)convertView.findViewById(R.id.checkedtextview);
        checkedTextView.setText(group.getHeader());
        checkedTextView.setChecked(isExpanded);

        return convertView;
    }



    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
