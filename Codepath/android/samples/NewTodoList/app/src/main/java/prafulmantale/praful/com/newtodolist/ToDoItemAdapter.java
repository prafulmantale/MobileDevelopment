package prafulmantale.praful.com.newtodolist;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

    private final List<ToDoItem> list;
    private final Activity context;

    public ToDoItemAdapter(Activity context, List<ToDoItem> list) {
        super(context, R.layout.nice_todo, list);

        this.list = list;
        this.context = context;
    }



    private class ViewHolder{

        TextView etItemText;
        ImageView ivSave;
        TextView tvDone;
        TextView tvSchedule;
        TextView tvPlace;
        TextView tvShare;
        TextView tvEdit;
        TextView tvRemove;

        void init(View convertView){

            etItemText = (TextView)convertView.findViewById(R.id.etTodoItemText_nice);
            ivSave = (ImageView)convertView.findViewById(R.id.ivSave);
            tvDone = (TextView)convertView.findViewById(R.id.tvDone);
            tvSchedule = (TextView)convertView.findViewById(R.id.tvSchedule);
            tvPlace = (TextView)convertView.findViewById(R.id.tvPlace);
            tvShare = (TextView)convertView.findViewById(R.id.tvShare);
            tvEdit = (TextView)convertView.findViewById(R.id.tvEdit);
            tvRemove = (TextView)convertView.findViewById(R.id.tvRemove);
        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ToDoItem item = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.nice_todo, null);
            viewHolder = new ViewHolder();
            viewHolder.init(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();

        }

        viewHolder.etItemText.setText(item.getItem());

        setupListeners(viewHolder);

        return convertView;
    }

    private void setupListeners(final ViewHolder viewHolder) {

        viewHolder.etItemText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    viewHolder.ivSave.setVisibility(View.VISIBLE);
                    v.requestFocus();
                }
                else{
                    viewHolder.ivSave.setVisibility(View.GONE);
                }
            }
        });

    }

    public interface ItemDetailsListener{
        public void OnItemDetailsRequested(int position);
    }
}
