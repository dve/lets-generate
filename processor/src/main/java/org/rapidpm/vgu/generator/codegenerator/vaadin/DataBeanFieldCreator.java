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

import org.rapidpm.vgu.generator.codegenerator.JPoetUtils;
import org.rapidpm.vgu.generator.model.PropertyModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public class DataBeanFieldCreator implements FieldCreator {

  private final PropertyModel propertyModel;

  public DataBeanFieldCreator(PropertyModel propertyModel) {
    this.propertyModel = propertyModel;
  }

  @Override
  public boolean isResponsibleFor(TypeName typeName) {
    return typeName.equals(JPoetUtils.getPropertyClassName(propertyModel));
  }

  @Override
  public int getPriority(FieldType fieldType) {
    return 0;
  }

  @Override
  public void createAndReturnFormField(Builder builder) {
    String fieldName = "field";
    builder.addStatement("$T $L = new $T()", getFormFieldClassName(), fieldName,
        getFormFieldClassName());
    builder.addStatement("$L.setClearButtonVisible(true)", fieldName);
    builder.addStatement("return $L", fieldName);
  }

  @Override
  public TypeName getFieldType() {
    return JPoetUtils.getPropertyClassName(propertyModel);
  }

  @Override
  public ClassName getFormFieldClassName() {
    return VaadinClassNameUtils.getComboboxClassName(propertyModel);
  }

  @Override
  public boolean allowsEmpty() {
    return true;
  }
}
