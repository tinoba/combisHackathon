package combis.hackathon.ui.login;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import combis.hackathon.R;
import combis.hackathon.data.api.models.request.UserInformation;
import combis.hackathon.injection.component.ActivityComponent;
import combis.hackathon.ui.base.activities.BaseActivity;
import combis.hackathon.ui.home.HomeActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends BaseActivity implements LoginView, EasyPermissions.PermissionCallbacks {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.facebook_button)
    LoginButton facebookButton;

    private static final int RC_SIGN_IN = 9001;
    private static final int AUDIO_PERMISSION_CODE = 10;

    private static final String TAG = LoginActivity.class.getName();

    private static final String CLIENT_ID = "981151316904-0g546k4u8lnncv8bhne98tj791ilrbsb.apps.googleusercontent.com";

    private CallbackManager callbackManager;

    private GoogleApiClient mGoogleApiClient;

    public String token;

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        if (presenter.checkIfLoggedIn() != 0) {
            startActivity(HomeActivity.createIntent(this));
        }

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        facebookButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebookButton.registerCallback(callbackManager, mCallback);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestProfile().requestIdToken(CLIENT_ID).build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestAudioPermission();
        presenter.setView(this);
    }

    @Override
    public void onPermissionsGranted(final int requestCode, final List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(final int requestCode, final List<String> perms) {
        finish();
    }

    @AfterPermissionGranted(AUDIO_PERMISSION_CODE)
    private void requestAudioPermission() {
        final String[] perms = {Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
        } else {
            EasyPermissions.requestPermissions(this, getResources().getString(R.string.audio_permission_request),
                                               AUDIO_PERMISSION_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick(R.id.google_button)
    public void googleClicked() {
        //googleLogin();

    }

    @Override
    public void goToHomeScreen() {
        startActivity(HomeActivity.createIntent(this));
    }

    private void googleLogin() {
        String[] accountTypes = new String[]{"com.google", "com.google.android.legacyimap"};
        Intent signInIntent = AccountPicker.newChooseAccountIntent(null, null,
                                                                   accountTypes, false, null, null, null, null);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(final LoginResult loginResult) {

            AccessToken accessToken = loginResult.getAccessToken();
            Log.e("TAG", accessToken.getToken());
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                try {
                    presenter.login(new UserInformation(object.getString("name"), object.getString("email")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            request.setParameters(parameters);
            request.executeAsync();

            if (accessToken.getToken() == null) {
                Log.i(TAG, "Neuspješna prijava");
            } else {/*
                NetworkService networkService = new NetworkService();
                Call<LoginResponse> call = networkService.getAPI().getApiKey("facebook", accessToken.getToken());
                call.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(final Call<LoginResponse> call, final Response<LoginResponse> response) {

                        if (response.body() == null) {
                            Log.i("TAG", "Greška na serveru");
                        } else {
                            String APIKEY = response.body().getData().getApi_key();
                            API_KEY = APIKEY;
                            ID = response.body().getData().getId();
                            Log.i("TAG", "on response " + APIKEY);
                            startActivity(new Intent(FoodActivity.this, RecyclerViewActivity.class));
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(final Call<LoginResponse> call, final Throwable t) {
                        Log.i("TAG", "on failure " + t.getMessage());
                        Log.i("TAG", "Neuspješno povezivanje sa serverom");
                    }
                });*/
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.e(TAG, "Neuspješna prijava");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        String mEmail;

        if (requestCode == RC_SIGN_IN) {
            if (data != null) {
                if (data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME) == null) {
                    Log.i("TAG", "Neuspješna priajva");
                } else {
                    mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Log.i("TAG", mEmail);
                    new GetGoogleToken(LoginActivity.this).execute(mEmail);
                }
            }
        }
    }

    private static final class GetGoogleToken extends AsyncTask<String, Void, Boolean> {

        public final LoginActivity loginActivity;

        public GetGoogleToken(final LoginActivity loginFragment) {
            this.loginActivity = loginFragment;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                loginActivity.token = GoogleAuthUtil
                        .getToken(loginActivity, params[0], "oauth2:" + Scopes.PROFILE + " " + Scopes.EMAIL + " " + Scopes.PLUS_LOGIN);
                Log.i("TAG", " google token " + loginActivity.token);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
                return false;
            } catch (UserRecoverableAuthException e) {
                loginActivity.startActivityForResult(e.getIntent(), loginActivity.RC_SIGN_IN);
                return false;
            } catch (GoogleAuthException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                if (loginActivity.token != null) {/*
                    NetworkService networkService = new NetworkService();
                    Call<LoginResponse> call = networkService.getAPI().getApiKey("google", loginActivity.token);
                    call.enqueue(new Callback<LoginResponse>() {

                        @Override
                        public void onResponse(final Call<LoginResponse> call, final Response<LoginResponse> response) {

                            if (response.body() != null) {
                                Log.i("TAG", "google apykey " + response.body().getData().getApi_key());
                                API_KEY =  response.body().getData().getApi_key();
                                ID = response.body().getData().getId();
                                loginActivity.startActivity(new Intent(loginActivity.getApplicationContext(), RecyclerViewActivity.class));
                                loginActivity.finish();

                            } else {
                                Log.i("TAG", "Neuspješno povezivanje s serverom");
                            }
                        }

                        @Override
                        public void onFailure(final Call<LoginResponse> call, final Throwable t) {
                            Log.i("TAG", "Neuspješno povezivanje s serverom");
                        }
                    });*/
                } else {
                    Log.i("TAG", "Neuspješna priajva");
                }
            }
        }
    }
}
