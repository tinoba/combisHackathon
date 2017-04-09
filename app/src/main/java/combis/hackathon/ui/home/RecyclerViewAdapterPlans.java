package combis.hackathon.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import combis.hackathon.R;
import combis.hackathon.data.api.models.response.PlansResponse;

public class RecyclerViewAdapterPlans extends RecyclerView.Adapter<RecyclerViewAdapterPlans.PlanViewHolder> {

    public interface Listener {

        Listener EMPTY = new EmptyListener();

        void getPlanIdAtPosition(long id, long transportId);
    }

    private Listener listener = Listener.EMPTY;

    List<PlansResponse> planInfoList = new ArrayList<>();

    @Override
    public RecyclerViewAdapterPlans.PlanViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new PlanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterPlans.PlanViewHolder holder, final int position) {
        holder.planName.setText(planInfoList.get(position).name);

        holder.hotelName.setText(planInfoList.get(position).hotelName);
        holder.planInfo.setText(planInfoList.get(position).destination);
    }

    @Override
    public int getItemCount() {
        return planInfoList.size();
    }

    public void setData(List<PlansResponse> data) {
        planInfoList.clear();
        planInfoList.addAll(data);
        notifyDataSetChanged();
    }

    public void setListener(final Listener listener) {
        this.listener = listener != null ? listener : Listener.EMPTY;
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.plan_item_name)
        protected TextView planName;

        @BindView(R.id.plan_item_hotel_name)
        protected TextView hotelName;

        @BindView(R.id.plan_item_info)
        protected TextView planInfo;

        @OnClick(R.id.plan_item_layout)
        public void onItemClicked(View view) {
            listener.getPlanIdAtPosition(planInfoList.get(getAdapterPosition()).id, planInfoList.get(getAdapterPosition()).id);
        }

        public PlanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final class EmptyListener implements Listener {

        @Override
        public void getPlanIdAtPosition(long id, long transportId) {
            //NO OP
        }
    }
}
