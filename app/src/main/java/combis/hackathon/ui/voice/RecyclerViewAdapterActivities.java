package combis.hackathon.ui.voice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import combis.hackathon.R;
import combis.hackathon.domain.model.ActivityModel;

public class RecyclerViewAdapterActivities extends RecyclerView.Adapter<RecyclerViewAdapterActivities.ActivityViewHolder> {

    public interface Listener {

        RecyclerViewAdapterActivities.Listener EMPTY = new RecyclerViewAdapterActivities.EmptyListener();

        void getActivityAtPosition(int position);
    }

    private RecyclerViewAdapterActivities.Listener listener = RecyclerViewAdapterActivities.Listener.EMPTY;

    List<ActivityModel> activityModelList = new ArrayList<>();

    @Override
    public RecyclerViewAdapterActivities.ActivityViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_item, parent, false);
        return new RecyclerViewAdapterActivities.ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterActivities.ActivityViewHolder holder, final int position) {
        holder.activityImage.setImageResource(activityModelList.get(position).getActivityImageId());
        holder.activityName.setText(activityModelList.get(position).getActivityName());
        holder.infoOne.setText(activityModelList.get(position).getActivityInfoOne());
        holder.infoTwo.setText(activityModelList.get(position).getActivityInfoTwo());
    }

    @Override
    public int getItemCount() {
        return activityModelList.size();
    }

    public void setData(List<ActivityModel> data) {
        activityModelList.clear();
        activityModelList.addAll(data);
        notifyDataSetChanged();
    }

    public void setListener(final RecyclerViewAdapterActivities.Listener listener) {
        this.listener = listener != null ? listener : RecyclerViewAdapterActivities.Listener.EMPTY;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_image)
        protected ImageView activityImage;

        @BindView(R.id.activity_item_name)
        protected TextView activityName;

        @BindView(R.id.activity_item_info_one)
        protected TextView infoOne;

        @BindView(R.id.activity_item_info_two)
        protected TextView infoTwo;

        @OnClick(R.id.activity_item_layout)
        public void onItemClicked(View view) {
            listener.getActivityAtPosition(getAdapterPosition());
        }

        public ActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final class EmptyListener implements RecyclerViewAdapterActivities.Listener {

        @Override
        public void getActivityAtPosition(int position) {
            //NO OP
        }
    }
}