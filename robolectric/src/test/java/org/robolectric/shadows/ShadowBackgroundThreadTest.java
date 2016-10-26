package org.robolectric.shadows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.TestRunners;

@RunWith(TestRunners.MultiApiWithDefaults.class)
public class ShadowBackgroundThreadTest {
  @Test
  public void reset() throws Exception {
    ShadowBackgroundThread.reset();
  }
}