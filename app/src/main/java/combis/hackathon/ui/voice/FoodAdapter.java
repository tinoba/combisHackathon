package combis.hackathon.ui.voice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.Foods;

public class FoodAdapter extends ArrayAdapter<Foods> {

    private List<Foods> foods;

    public FoodAdapter(final Context context, List<Foods> foods) {
        super(context, R.layout.food_row, foods);

        this.foods = foods;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.food_row, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.cijenaJelo.setText(String.valueOf(foods.get(position).price));
        holder.imeJela.setText(foods.get(position).name);
        holder.ratingBar.setRating(foods.get(position).rating);
        holder.textJelo.setText(foods.get(position).text);
        Picasso.with(getContext())
               .load(foods.get(position).idSlike)
               .into(holder.slikaJela);

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.imeJela)
        TextView imeJela;
        @BindView(R.id.podaci_jelo)
        TextView textJelo;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.mala_pizza)
        TextView cijenaJelo;
        @BindView(R.id.slikaJela)
        ImageView slikaJela;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
