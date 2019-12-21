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
package org.rapidpm.vgu.generator.codegenerator.vaadin;

import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import org.rapidpm.vgu.generator.model.PropertyModel;
import com.squareup.javapoet.ClassName;

public class VaadinClassNameUtils {
  private VaadinClassNameUtils() {
    throw new IllegalAccessError("Utility class");
  }

  public static ClassName getItemLabelGeneratorClassName(DataBeanModel model) {
    return ClassName.get(model.getPkg() + ".vaadin", model.getName() + "ItemLabelGenerator");
  }

  public static ClassName getComboboxClassName(DataBeanModel model) {
    return getComboboxClassName(model.getPkg(), model.getName());
  }

  public static ClassName getComboboxClassName(PropertyModel model) {
    return getComboboxClassName(model.getType());
  }

  private static ClassName getComboboxClassName(String packageName, String className) {
    return ClassName.get(packageName + ".vaadin", className + "ComboBox");
  }

  public static ClassName getComboboxClassName(TypeMirror type) {
    DeclaredType dt = (DeclaredType) type;
    String simpleName = ((QualifiedNameable) dt.asElement()).getSimpleName().toString();
    String fullName = ((QualifiedNameable) dt.asElement()).getQualifiedName().toString();
    String packageName = fullName.substring(0, fullName.length() - simpleName.length() - 1);

    return getComboboxClassName(packageName, simpleName);
  }


}
