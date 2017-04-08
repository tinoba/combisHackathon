package combis.hackathon.ui.photo;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;

public class TakeOrPickAPhotoActivity extends BaseActivity implements TakeOrPickAPhotoView{

    @Inject
    TakeOrPickAPhotoPresenter presenter;

    @BindView(R.id.slikica)
    ImageView slikica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_or_pick_aphoto);

        ButterKnife.bind(this);

    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getImagesPath(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dispose();
    }
    @Override
    public void showImagesPath(final List<String> imageList) {
        Picasso.with(this).load(new File(imageList.get(0))).into(slikica);

    }
}
