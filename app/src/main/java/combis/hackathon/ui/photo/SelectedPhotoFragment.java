package combis.hackathon.ui.photo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import combis.hackathon.R;

public class SelectedPhotoFragment extends Fragment {

    private static final int COMPRESS_QUALITY = 100;

    private static final String ARGUMENTS = "arguments";
    private String data;
    Bitmap canvasImage;

    private boolean isPhotoClicked = false;

    @BindView(R.id.selected_image)
    ImageView selectedImage;

    @BindView(R.id.layout)
    RelativeLayout relativeLayout;

    @BindView(R.id.take_a_photo)
    ImageView takeAPhoto;

    @BindView(R.id.send_a_photo)
    ImageView sendAPhoto;

    @BindView(R.id.take_photo_send_photo_layout)
    LinearLayout linearLayout;

    public SelectedPhotoFragment() {
    }

    public static SelectedPhotoFragment newIstance(String example_argument) {
        SelectedPhotoFragment selectedPhotoFragment = new SelectedPhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENTS, example_argument);
        selectedPhotoFragment.setArguments(args);
        return selectedPhotoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.selected_photo_fragment, container, false);

        ButterKnife.bind(this, v);

        data = getArguments().getString(ARGUMENTS);

//        Glide.with(getActivity()).load(data)
//             .placeholder(R.drawable.ic_gallery_placeholder).centerCrop()
//             .into(selectedImage);


        Glide
                .with(this)
                .load(data)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(300, 300) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        selectedImage.setImageBitmap(resource);
                        canvasImage = resource;
                    }
                });

//        Picasso.with(getActivity()).load(data).placeholder(R.drawable.ic_gallery_placeholder).into(selectedImage);

        selectedImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                if (isPhotoClicked) {
                    linearLayout.setVisibility(View.GONE);
                    selectedImage.setAlpha(1f);
                    isPhotoClicked = false;
                } else {
                    isPhotoClicked = true;
                    linearLayout.setVisibility(View.VISIBLE);
                    selectedImage.setAlpha(0.4f);
                }
            }
        });

        takeAPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                Toast.makeText(getActivity(), "TAKE", Toast.LENGTH_SHORT).show();
            }
        });

        sendAPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
//                Bitmap canvasImage = ((BitmapDrawable) selectedImage.getDrawable()).getBitmap();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                canvasImage.compress(Bitmap.CompressFormat.JPEG, COMPRESS_QUALITY, bs);
                byte[] bytes = bs.toByteArray();

                String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
                Log.d("Signature", encodedImage);
                //todo here call request and open new activity with image info on another screen
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedImage.setAlpha(1f);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        relativeLayout.removeAllViews();
        data = null;
        System.gc();
        Runtime.getRuntime().gc();
    }
}
