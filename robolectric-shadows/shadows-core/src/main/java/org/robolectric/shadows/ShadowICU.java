package org.robolectric.shadows;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.util.Locale;

import static android.os.Build.VERSION_CODES;
import static android.os.Build.VERSION_CODES.KITKAT_WATCH;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Shadow for {@link libcore.icu.ICU}.
 */
@Implements(value = libcore.icu.ICU.class, isInAndroidSdk = false)
public class ShadowICU {

  @Implementation
  public static String addLikelySubtags(String locale) {
    return "en-US";
  }

  @Implementation(minSdk = LOLLIPOP)
  public static String getBestDateTimePattern(String skeleton, Locale locale) {
    return skeleton;
  }

  @Implementation(maxSdk = KITKAT_WATCH)
  public static String getBestDateTimePattern(String skeleton, String locale) {
    return skeleton;
  }
}
