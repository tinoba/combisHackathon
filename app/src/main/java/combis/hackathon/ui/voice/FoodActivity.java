package combis.hackathon.ui.voice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.Foods;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class FoodActivity extends BaseActivity {

    @Inject
    FoodPresenter presenter;

    public static Intent createIntent(final Context context) {
        return new Intent(context, FoodActivity.class);
    }

    @BindView(R.id.food_list)
    ListView foodList;

    @BindView(R.id.activity_home_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.order_food);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Timber.e("foods");
        List<Foods> foodsList = new ArrayList<>();
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, onions, lepinja and kajmak"));
        foodsList.add(new Foods("Chiken", R.drawable.chicken, 55.00, 3f, "Chicken with pommes or rice"));
        foodsList.add(new Foods("Pizza mexicana", R.drawable.pizza_mexicana, 43.00, 4.5f, "Ketchup, peperoni, chili, beans, corn"));
        foodsList.add(new Foods("Octopus", R.drawable.hobotnica, 72.00, 5, "Octopus with vegetables and potatos"));
        foodsList.add(new Foods("Hamburger", R.drawable.hamburger, 35.00, 2f, "Burger with green salad, tomatoes and pommes"));
        foodsList.add(new Foods("Mussels", R.drawable.dagnje, 80.00, 4.5f, "Mussels with potatos and salad"));

        foodList.setAdapter(new FoodAdapter(this, foodsList, presenter));
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
