package ru.smartislav.clock.rdtsc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class RdTscSource {
  static {
    String os = System.getProperty("os.name", "").toLowerCase();
    String arch = System.getProperty("os.arch", "").toLowerCase();

    String suffix = "";
    if (os.equals("mac os x") && arch.equals("x86_64")) {
      suffix = "macosx.jnilib";
    } else if (os.equals("linux")) {
      if (arch.equals("amd64")) {
        suffix = "linux-64.so";
      } else if (arch.equals("x86")) {
        suffix = "linux-32.so";
      }
    } else if (os.startsWith("windows")) {
      if (arch.equals("amd64")) {
        suffix = "win-64.dll";
      } else if (arch.equals("x86")) {
        suffix = "win-32.dll";
      }
    }

    InputStream in = RdTscSource.class.getClassLoader().getResourceAsStream("ru/smartislav/microclock/jni/native-" + suffix);
    if (in != null) {
      try {
        File tempFile = File.createTempFile("ru.smartislav.microclock.native-", suffix);
        tempFile.deleteOnExit();
        OutputStream tempLib = new FileOutputStream(tempFile);
        try {
          byte[] buf = new byte[4096];
          int len;
          do {
            len = in.read(buf);
            tempLib.write(buf, 0, len);
          } while (len == buf.length);
        } finally {
          tempLib.close();
        }
        System.load(tempFile.getPath());
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static native boolean isRdtscConstant();

  public static native long rdtsc();
}
