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
package de.generator.beans.vaadin;

import static java.lang.System.setProperty;
import org.apache.meecrowave.Meecrowave;

public class Runner {
  private Runner() {
    throw new IllegalAccessError("Utility class");
  }

  public static void main(String[] args) throws Exception {
    new Meecrowave(new Meecrowave.Builder() {
      {
        setProperty("vaadin.i18n.provider", I18N.class.getName());
        setHttpPort(8080);
        setTomcatScanning(true);
        setTomcatAutoSetup(true);
        setHttp2(true);

      }
    }).bake().await();
  }
}
