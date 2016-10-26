package org.robolectric.shadows;

import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import java.util.List;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import static android.os.Build.VERSION_CODES;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR2;

/**
 * Shadow for {@link android.telephony.TelephonyManager}.
 */
@Implements(TelephonyManager.class)
public class ShadowTelephonyManager {
  private PhoneStateListener listener;
  private int eventFlags;
  private String deviceId;
  private String groupIdLevel1;
  private String networkOperatorName;
  private String networkCountryIso;
  private String networkOperator;
  private String simOperator;
  private String simOperatorName;
  private boolean readPhoneStatePermission = true;
  private int phoneType = TelephonyManager.PHONE_TYPE_GSM;
  private String simCountryIso;
  private int simState = TelephonyManager.SIM_STATE_READY;
  private String line1Number;
  private int networkType;
  private List<CellInfo> allCellInfo;
  private CellLocation cellLocation;

  @Implementation
  public void listen(PhoneStateListener listener, int events) {
    this.listener = listener;
    this.eventFlags = events;
  }

  /**
   * Non-Android accessor.  Returns the most recent listener passed to #listen().
   *
   * @return Phone state listener.
   */
  public PhoneStateListener getListener() {
    return listener;
  }

  /**
   * Non-Android accessor.  Returns the most recent flags passed to #listen().
   *
   * @return Event flags.
   */
  public int getEventFlags() {
    return eventFlags;
  }

  @Implementation
  public String getDeviceId() {
    checkReadPhoneStatePermission();
    return deviceId;
  }

  public void setDeviceId(String newDeviceId) {
    deviceId = newDeviceId;
  }

  public void setNetworkOperatorName(String networkOperatorName) {
    this.networkOperatorName = networkOperatorName;
  }

  @Implementation
  public String getNetworkOperatorName() {
    return networkOperatorName;
  }

  public void setNetworkCountryIso(String networkCountryIso) {
    this.networkCountryIso = networkCountryIso;
  }

  @Implementation
  public String getNetworkCountryIso() {
    return networkCountryIso;
  }

  public void setNetworkOperator(String networkOperator) {
    this.networkOperator = networkOperator;
  }

  @Implementation
  public String getNetworkOperator() {
    return networkOperator;
  }

  @Implementation
  public String getSimOperator() {
    return simOperator;
  }

  public void setSimOperator(String simOperator) {
    this.simOperator = simOperator;
  }

  @Implementation
  public String getSimOperatorName() {
    return simOperatorName;
  }

  public void setSimOperatorName(String simOperatorName) {
    this.simOperatorName = simOperatorName;
  }

  @Implementation
  public String getSimCountryIso() {
    return simCountryIso;
  }

  public void setSimCountryIso(String simCountryIso) {
    this.simCountryIso = simCountryIso;
  }

  @Implementation
  public int getSimState() {
    return simState;
  }

  public void setSimState(int simState) {
    this.simState = simState;
  }

  public void setReadPhoneStatePermission(boolean readPhoneStatePermission) {
    this.readPhoneStatePermission = readPhoneStatePermission;
  }

  private void checkReadPhoneStatePermission() {
    if (!readPhoneStatePermission) {
      throw new SecurityException();
    }
  }

  @Implementation
  public int getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(int phoneType) {
    this.phoneType = phoneType;
  }

  @Implementation
  public String getLine1Number() {
    return line1Number;
  }

  public void setLine1Number(String line1Number) {
    this.line1Number = line1Number;
  }

  @Implementation
  public int getNetworkType() {
    return networkType;
  }

  public void setNetworkType(int networkType) {
    this.networkType = networkType;
  }

  @Implementation(minSdk = JELLY_BEAN_MR1)
  public List<CellInfo> getAllCellInfo() {
    return allCellInfo;
  }

  public void setAllCellInfo(List<CellInfo> allCellInfo) {
    this.allCellInfo = allCellInfo;
  }

  @Implementation
  public CellLocation getCellLocation() {
    return this.cellLocation;
  }

  public void setCellLocation(CellLocation cellLocation) {
    this.cellLocation = cellLocation;
  }

  @Implementation(minSdk = JELLY_BEAN_MR2)
  public String getGroupIdLevel1() {
    return this.groupIdLevel1;
  }

  public void setGroupIdLevel1(String groupIdLevel1) {
    this.groupIdLevel1 = groupIdLevel1;
  }
}
