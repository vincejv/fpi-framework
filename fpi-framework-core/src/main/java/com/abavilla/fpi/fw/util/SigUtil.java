/*************************************************************************
 * Copyright (C) 2022 Vince Jerald Villamora @ https://vincejv.com       *
 *                                                                       *
 * Licensed under the Apache License, Version 2.0 (the "License");       *
 * you may not use this file except in compliance with the License.      *
 * You may obtain a copy of the License at                               *
 *                                                                       *
 *   http://www.apache.org/licenses/LICENSE-2.0                          *
 *                                                                       *
 * Unless required by applicable law or agreed to in writing, software   *
 * distributed under the License is distributed on an "AS IS" BASIS,     *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or       *
 * implied. See the License for the specific language governing          *
 * permissions and limitations under the License.                        *
 *************************************************************************/

package com.abavilla.fpi.fw.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.bouncycastle.util.encoders.Hex;

/**
 * Utility class for verifying data through HMAC hashing.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SigUtil {

  /**
   * HMAC Hash algorithm to use
   */
  private static final String HMAC_SHA256_ALGO = "HmacSHA256";

  /**
   * Calculates the HMAC signature of a given string data value using {@link #HMAC_SHA256_ALGO} using the given key
   *
   * @param data The string data to encode
   * @param key HMAC Key to use
   * @return the resulting signature
   */
  @SneakyThrows
  private static byte[] calculateHmac(String data, String key)  {
    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGO);
    Mac mac = Mac.getInstance(HMAC_SHA256_ALGO);
    mac.init(secretKeySpec);
    return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Verifies a given string payload against an HMAC signature and key.
   *
   * @param payload the string payload
   * @param key the HMAC key
   * @param signatureToVerify the HMAC signature
   * @return {@code true} if signature matches otherwise {@code false}
   */
  public static boolean validateSignature(String payload, String key, String signatureToVerify) {
    byte[] hash = calculateHmac(payload, key);
    return Arrays.equals(Hex.encode(hash), signatureToVerify.getBytes(StandardCharsets.UTF_8));
  }

}
