package org.robolectric.shadows;

import android.net.Network;

import org.robolectric.annotation.Implements;
import org.robolectric.internal.Shadow;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Shadow for {@code android.net.Network}.
 */
@Implements(value = Network.class, minSdk = LOLLIPOP)
public class ShadowNetwork {
  private int netId;

  /**
   * Creates new instance of {@link Network}, because its constructor is hidden.
   *
   * @param netId The netId.
   * @return The Network instance.
   */
  public static Network newInstance(int netId) {
    Network network = Shadow.newInstance(Network.class, new Class[]{int.class}, new Object[]{netId});
    return network;
  }

  public void __constructor__(int netId) {
    this.netId = netId;
  }

  /**
   * Allows to get the stored netId.
   *
   * @return The netId.
   */
  public int getNetId() {
    return netId;
  }
}