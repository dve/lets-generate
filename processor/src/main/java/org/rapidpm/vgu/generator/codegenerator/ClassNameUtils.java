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
package org.rapidpm.vgu.generator.codegenerator;

import org.apache.commons.lang3.StringUtils;
import org.rapidpm.vgu.generator.model.DataBeanModel;

public class ClassNameUtils {
  private ClassNameUtils() {
    throw new IllegalAccessError("Utility class");
  }

  public static String prefixCamelCase(String prefix, String suffix) {
    return prefix + capitalizeFirstLetter(suffix);
  }

  public static String capitalizeFirstLetter(String original) {
    if (original == null || original.length() == 0) {
      return original;
    }
    return original.substring(0, 1).toUpperCase() + original.substring(1);
  }

  public static String getPackageName(String qualifiedName) {
    return qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
  }

  public static String getSimpleName(String qualifiedName) {
    return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
  }

  public static String appendSubPackage(String qualifiedName, String subPackageName) {
    return StringUtils.join(
        new String[] {getPackageName(qualifiedName), subPackageName, getSimpleName(qualifiedName)},
        ".");
  }

  public static String toEnumName(String camelCasedString) {
    if (camelCasedString == null || camelCasedString.isEmpty())
      return camelCasedString;

    StringBuilder result = new StringBuilder();
    // result.append(camelCasedString.charAt(0));
    for (int i = 0; i < camelCasedString.length(); i++) {
      if (Character.isUpperCase(camelCasedString.charAt(i)))
        result.append("_");
      result.append(Character.toUpperCase(camelCasedString.charAt(i)));
    }
    return result.toString();
  }

  public static String getFilterPackage(DataBeanModel model) {
    return model.getPackage() + ".filter";
  }
}
