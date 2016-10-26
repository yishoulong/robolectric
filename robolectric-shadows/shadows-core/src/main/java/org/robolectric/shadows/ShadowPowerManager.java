package org.robolectric.shadows;

import android.os.PowerManager;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.Resetter;
import org.robolectric.internal.Shadow;

import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES;
import static android.os.Build.VERSION_CODES.KITKAT_WATCH;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.shadows.ShadowApplication.getInstance;

/**
 * Shadow for {@link android.os.PowerManager}.
 */
@Implements(PowerManager.class)
public class ShadowPowerManager {
  private boolean isScreenOn = true;
  private boolean isInteractive = true;
  private boolean isPowerSaveMode = false;

  @Implementation
  public PowerManager.WakeLock newWakeLock(int flags, String tag) {
    PowerManager.WakeLock wl = Shadow.newInstanceOf(PowerManager.WakeLock.class);
    getInstance().addWakeLock(wl);
    return wl;
  }

  @Implementation
  public boolean isScreenOn() {
    return isScreenOn;
  }

  public void setIsScreenOn(boolean screenOn) {
    isScreenOn = screenOn;
  }

  @Implementation(minSdk = KITKAT_WATCH)
  public boolean isInteractive() {
    return isInteractive;
  }

  public void setIsInteractive(boolean interactive) {
    isInteractive = interactive;
  }

  @Implementation(minSdk = KITKAT_WATCH)
  public boolean isPowerSaveMode() {
    return isPowerSaveMode;
  }

  public void setIsPowerSaveMode(boolean powerSaveMode) {
    isPowerSaveMode = powerSaveMode;
  }

  private Map<Integer, Boolean> supportedWakeLockLevels = new HashMap<>();

  @Implementation(minSdk = LOLLIPOP)
  public boolean isWakeLockLevelSupported(int level) {
    return supportedWakeLockLevels.containsKey(level) ? supportedWakeLockLevels.get(level) : false;
  }

  public void setIsWakeLockLevelSupported(int level, boolean supported) {
    supportedWakeLockLevels.put(level, supported);
  }

  /**
   * Non-Android accessor that discards the most recent {@code PowerManager.WakeLock}s
   */
  @Resetter
  public static void reset() {
    ShadowApplication shadowApplication = ShadowApplication.getInstance();
    if (shadowApplication != null) {
      shadowApplication.clearWakeLocks();
    }
  }

  /**
   * Non-Android accessor retrieves the most recent wakelock registered
   * by the application
   *
   * @return Most recent wake lock.
   */
  public static PowerManager.WakeLock getLatestWakeLock() {
    return shadowOf(RuntimeEnvironment.application).getLatestWakeLock();
  }

  @Implements(PowerManager.WakeLock.class)
  public static class ShadowWakeLock {
    private boolean refCounted = true;
    private int refCount;
    private boolean locked;

    @Implementation
    public void acquire() {
      acquire(0);

    }

    @Implementation
    public synchronized void acquire(long timeout) {
      if (refCounted) {
        refCount++;
      } else {
        locked = true;
      }
    }

    @Implementation
    public synchronized void release() {
      if (refCounted) {
        if (--refCount < 0) throw new RuntimeException("WakeLock under-locked");
      } else {
        locked = false;
      }
    }

    @Implementation
    public synchronized boolean isHeld() {
      return refCounted ? refCount > 0 : locked;
    }

    /**
     * Non-Android accessor retrieves if the wake lock is reference counted or not
     *
     * @return Is the wake lock reference counted?
     */
    public boolean isReferenceCounted() {
      return refCounted;
    }

    @Implementation
    public void setReferenceCounted(boolean value) {
      refCounted = value;
    }
  }
}
