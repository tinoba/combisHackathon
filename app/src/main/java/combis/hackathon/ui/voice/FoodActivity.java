package combis.hackathon.ui.voice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.domain.model.Foods;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class FoodActivity extends BaseActivity {

    public static Intent createIntent(final Context context) {
        return new Intent(context, FoodActivity.class);
    }

    @BindView(R.id.food_list)
    ListView foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        Timber.e("foods");
        List<Foods> foodsList = new ArrayList<>();
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));
        foodsList.add(new Foods("Čevapi", R.drawable.cevapi, 30.00, 5, "Čevapi, luk, lepinja, kajmak"));

        foodList.setAdapter(new FoodAdapter(this, foodsList));
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
