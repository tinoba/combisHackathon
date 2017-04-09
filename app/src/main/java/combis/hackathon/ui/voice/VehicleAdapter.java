package combis.hackathon.ui.voice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.Vehicle;
import combis.hackathon.ui.home.HomeActivity;

public class VehicleAdapter extends ArrayAdapter<Vehicle> {

    private List<Vehicle> vehicles;

    public VehicleAdapter(final Context context, List<Vehicle> vehicles) {
        super(context, R.layout.vehicle_row, vehicles);

        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.vehicle_row, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.imageButton.setOnClickListener(v -> {
            getContext().startActivity(new Intent(getContext(), HomeActivity.class));
            Toast.makeText(getContext(), "Transportation booked", Toast.LENGTH_SHORT).show();
        });
        holder.agencijaName.setText(vehicles.get(position).agencyName);
        holder.cijena.setText(vehicles.get(position).cijena + " kn/km");
        holder.radnoVrijeme.setText(vehicles.get(position).radnoVrijeme);
        Picasso.with(getContext())
               .load(vehicles.get(position).vehicleImage)
               .into(holder.vehicleImage);

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.agencija_name)
        TextView agencijaName;
        @BindView(R.id.cijena)
        TextView cijena;
        @BindView(R.id.radno_vrijeme)
        TextView radnoVrijeme;
        @BindView(R.id.vehicle_image)
        ImageView vehicleImage;

        @BindView(R.id.order_vehicle)
        ImageButton imageButton;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
