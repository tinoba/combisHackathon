package combis.hackathon.data.storage;

import android.content.SharedPreferences;

public final class TemplatePreferences implements PreferenceRepository {

    private static final String KEY_USER_ID = "key_user_id";

    private static final String KEY_APY_KEY = "key_api_key";

    private static final String EMPTY_STRING = "";

    private static final long EMPTY_USER_ID = 0;

    private final SharedPreferences secureDelegate;

    public static TemplatePreferences create(final SharedPreferences secureDelegate) {
        return new TemplatePreferences(secureDelegate);
    }

    private TemplatePreferences(final SharedPreferences secureDelegate) {
        this.secureDelegate = secureDelegate;
    }

    @Override
    public void setUserId(final long userId) {
        secureDelegate.edit().putLong(KEY_USER_ID, userId).apply();
    }

    @Override
    public long getUserId() {
        return secureDelegate.getLong(KEY_USER_ID, EMPTY_USER_ID);
    }

    @Override
    public void setHotelId(final long hotelId) {
        secureDelegate.edit().putLong(KEY_APY_KEY, hotelId).apply();
    }

    @Override
    public long getHotelId() {
        return secureDelegate.getLong(KEY_APY_KEY, 0);
    }
}
