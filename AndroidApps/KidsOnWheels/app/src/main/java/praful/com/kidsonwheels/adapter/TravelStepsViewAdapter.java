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
import praful.com.kidsonwheels.model.TravelStep;

/**
 * Created by prafulmantale on 4/14/15.
 */
public class TravelStepsViewAdapter extends RecyclerView.Adapter<TravelStepsViewAdapter.TravelStepsViewHolder> {

    private List<TravelStep> mTravelSteps;

    public TravelStepsViewAdapter(List<TravelStep> travelSteps) {
        mTravelSteps = travelSteps;
    }


    public static class TravelStepsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView distance;
        TextView instructions;

        TravelStepsViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.maneuvar_image);
            distance = (TextView) itemView.findViewById(R.id.distance_text);
            instructions = (TextView) itemView.findViewById(R.id.maneuver_text);
        }

    }

    @Override
    public int getItemCount() {
        return mTravelSteps.size();
    }


    @Override
    public TravelStepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.travel_step_items, viewGroup, false);
        TravelStepsViewHolder pvh = new TravelStepsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TravelStepsViewHolder viewHolder, int i) {
        viewHolder.instructions.setText(mTravelSteps.get(i).getDisplayInstruction());
        //viewHolder.image.setImageResource();
        viewHolder.distance.setText(mTravelSteps.get(i).getDistance().getText());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
