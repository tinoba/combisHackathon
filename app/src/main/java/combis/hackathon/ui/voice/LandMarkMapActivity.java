package combis.hackathon.ui.voice;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import combis.hackathon.R;
import combis.hackathon.domain.model.MarkerModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class LandMarkMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(48.856614, 2.3522219);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14f);

        //TODO TEST DATA GET REAL DATA WHEN NEEDED
//        LatLng landmark = new LatLng(48.8584, 2.2945);
//        MarkerOptions markerOptions = new MarkerOptions().position(landmark).title("Eiffel Tower");
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
//                getMarkerBitmapFromView(getResources().getDrawable(R.mipmap.ic_launcher), "Eiffel Tower",
//                                        "Most popular landmark in Paris!")));
//        mMap.addMarker(markerOptions);

        List<MarkerModel> markerModelList = new ArrayList<MarkerModel>();
        markerModelList.add(
                new MarkerModel(new LatLng(48.8584, 2.2945), "Eiffel Tower", "Most popular landmark in Paris!", R.drawable.eiffelov_toranj, getString(R.string.landmark_eiff)));
        markerModelList.add(
                new MarkerModel(new LatLng(48.8462, 2.3464), "Panthéon", "Is a building in the Latin Quarter!", R.drawable.pantheon, getString(R.string.landmark_panth)));
        markerModelList.add(
                new MarkerModel(new LatLng(48.8738, 2.2950), "Arc de Triomphe", "Is one of the most famous monuments!", R.drawable.arc, getString(R.string.landmark_arc)));
        markerModelList.add(
                new MarkerModel(new LatLng(48.8596, 2.3369), "Musee du Louvre", "Is the world's largest museum!", R.drawable.mousee, getString(R.string.landmark_musee)));
        markerModelList.add(
                new MarkerModel(new LatLng(48.8530, 2.3499), "Notre Dame de Paris", "Is a medieval Catholic cathedral!", R.drawable.notre, getString(R.string.landmark_notre)));

        addMarkerToMap(markerModelList);
        mMap.setOnMarkerClickListener(marker -> {
            showDialogMap(marker.getTitle(), markerModelList);
            return true;
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(zoom);
    }

    private void addMarkerToMap(List<MarkerModel> markerModelList) {
        for (final MarkerModel markerModel : markerModelList) {
            MarkerOptions markerOptions = new MarkerOptions().position(markerModel.getLatLng()).title(markerModel.getLandMarkTitle());
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
                    getMarkerBitmapFromView(getResources().getDrawable(markerModel.getLandMarkId()), markerModel.getLandMarkTitle(),
                                            markerModel.getSmallDescription())));
            mMap.addMarker(markerOptions);
        }
    }

    private Bitmap getMarkerBitmapFromView(Drawable drawable1, String outletName, String outletProduct) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.landmark_image);
        TextView outletTitle = (TextView) customMarkerView.findViewById(R.id.landmark_title);
        TextView outletProductCustom = (TextView) customMarkerView.findViewById(R.id.landmard_info);

        markerImageView.setImageDrawable(drawable1);
//        Picasso.with(getApplicationContext()).load(image).into(markerImageView);
        outletTitle.setText(outletName);
        outletProductCustom.setText(outletProduct);

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();

        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                                                    Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null) {
            drawable.draw(canvas);
        }
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    private void showDialogMap(String id, List<MarkerModel> markerModelList) {
        MarkerModel clickedMarker = null;
        for (final MarkerModel markerModel : markerModelList) {
            if (String.valueOf(markerModel.getLandMarkTitle()).equals(id)) {
                clickedMarker = markerModel;
            }
        }

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.map_marker_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView landMarkTitle = (TextView) dialog.findViewById(R.id.name);
        CircleImageView landMarkImage = (CircleImageView) dialog.findViewById(R.id.landmark_image);
        TextView bigDiscription = (TextView) dialog.findViewById(R.id.description);
        CircleImageView close = (CircleImageView) dialog.findViewById(R.id.close);
        Button button = (Button) dialog.findViewById(R.id.button);

        landMarkTitle.setText(clickedMarker.getLandMarkTitle());
        bigDiscription.setText(clickedMarker.getBigDescription());
        landMarkImage.setImageResource(clickedMarker.getLandMarkId());
        close.setOnClickListener(view -> dialog.dismiss());
        button.setOnClickListener(view -> startActivity(new Intent(LandMarkMapActivity.this, VehicleActivity.class)));


//        WebView wv = new WebView(this);
//        wv.loadUrl("https://en.wikipedia.org/wiki/" + clickedMarker.getLandMarkTitle());
//        wv.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//
//                return true;
//            }
//        });
//
//        dialog.setContentView(wv);

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
