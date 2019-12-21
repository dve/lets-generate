/**
 * Copyright © 2019 Daniel Nordhoff-Vergien (dve@vergien.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.vgu.generator.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

/**
 * A helper class for reading and writing Services files.
 */
final class TranslationFiles {
  public static final String SERVICES_PATH = "translation.properties";

  private TranslationFiles() {
    throw new IllegalAccessError("Utility class");
  }

  /**
   * Returns an absolute path to a service file given the class name of the service.
   *
   * @param serviceName not {@code null}
   * @return SERVICES_PATH + serviceName
   */
  static String getPath(String serviceName) {
    return SERVICES_PATH + "/" + serviceName;
  }

  /**
   * Reads the set of service classes from a service file.
   *
   * @param input not {@code null}. Closed after use.
   * @return a not {@code null Set} of service class names.
   * @throws IOException
   */
  static TreeMap<String, String> readServiceFile(InputStream input) throws IOException {
    Properties serviceClasses = new Properties();
    serviceClasses.load(input);

    return new TreeMap<>((Map) serviceClasses);
  }

  /**
   * Writes the set of service class names to a service file.
   *
   * @param output not {@code null}. Not closed after use.
   * @param services a not {@code null Collection} of service class names.
   * @throws IOException
   */
  static void writeServiceFile(Map<String, String> services, OutputStream output)
      throws IOException {
    Properties properties = new SortedProperies();

    for (Entry<String, String> entry : services.entrySet()) {
      properties.put(entry.getKey(), entry.getValue());
    }
    properties.store(output, "Generated " + LocalDateTime.now().toString());
  }
}
