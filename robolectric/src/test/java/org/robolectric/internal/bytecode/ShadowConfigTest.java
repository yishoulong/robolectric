package org.robolectric.internal.bytecode;

import org.junit.Test;
import org.robolectric.annotation.Implementation;

import java.lang.reflect.Method;

public class ShadowConfigTest {
  @Test
  public void supportsSdk() throws Exception {
    ShadowConfig shadowConfig = new ShadowConfig("some-class", false, false, false, -1, -1);
//    shadowConfig.methodSupportsSdk(getMethod("methodWithoutFromOrTo"))
//    System.out.println(implementation);
  }

  private Method getMethod(String methodWithoutFromOrTo) throws NoSuchMethodException {
    return ShadowConfigTest.class
          .getMethod(methodWithoutFromOrTo);
  }

  @Implementation
  void methodWithoutFromOrTo() {
  }

  @Implementation(from = 20)
  void methodWithFrom() {
  }

  @Implementation(to = 20)
  void methodWithTo() {
  }

  @Implementation(from = 19, to = 21)
  void methodWithFromAndTo() {
  }

  @Implementation(from = 20, to = 20)
  void methodWithSameFromAndTo() {
  }
}