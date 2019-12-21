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

import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import org.rapidpm.vgu.generator.model.PropertyModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class JPoetUtils {
  private JPoetUtils() {
    throw new IllegalAccessError("Utility class");
  }

  public static ClassName getFilterClassName(TypeMirror type) {
    DeclaredType dt = (DeclaredType) type;
    String simpleName = ((QualifiedNameable) dt.asElement()).getSimpleName().toString();
    String fullName = ((QualifiedNameable) dt.asElement()).getQualifiedName().toString();
    String packageName = fullName.substring(0, fullName.length() - simpleName.length() - 1);
    return getFilterClassName(packageName, simpleName);
  }

  public static ClassName getFilterClassName(DataBeanModel model) {
    return getFilterClassName(model.getPkg(), model.getName());
  }

  public static ClassName getFilterClassName(String packageName, String simpleName) {
    return ClassName.get(packageName + ".filter", simpleName + "Filter");
  }

  public static ClassName getBaseQueriesClassName(TypeMirror type) {
    DeclaredType dt = (DeclaredType) type;
    String simpleName = ((QualifiedNameable) dt.asElement()).getSimpleName().toString();
    String fullName = ((QualifiedNameable) dt.asElement()).getQualifiedName().toString();
    String packageName = fullName.substring(0, fullName.length() - simpleName.length() - 1);
    return ClassName.get(packageName + ".repo", simpleName + "BaseQueries");
  }

  public static ClassName getBaseQueriesClassName(DataBeanModel model) {
    return ClassName.get(model.getPkg() + ".repo", model.getName() + "BaseQueries");
  }

  public static ClassName getBeanClassName(DataBeanModel model) {
    return ClassName.get(model.getPkg(), model.getName());
  }

  public static ClassName getSortPropertyClassName(DataBeanModel model) {
    return ClassName.get(ClassNameUtils.getFilterPackage(model), model.getName() + "SortFields");
  }

  public static TypeName getPropertyClassName(PropertyModel model) {
    return ClassName.get(model.getType());
  }
}
