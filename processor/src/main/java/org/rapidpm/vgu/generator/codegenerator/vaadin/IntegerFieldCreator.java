/*
 * Copyright © 2021 Daniel Nordhoff-Vergien (dve@vergien.net)
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

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@AutoService(FieldCreator.class)
public class IntegerFieldCreator implements FieldCreator {

  @Override
  public boolean isResponsibleFor(TypeName typeName) {
    return TypeName.INT.equals(typeName) || TypeName.INT.box().equals(typeName);
  }

  @Override
  public int getPriority(FieldType fieldType) {
    return 0;
  }

  @Override
  public void createAndReturnFormField(Builder builder) {
    String fieldName = "field";
    builder.addStatement("$T $L = new $T()", getFormFieldClassName(), fieldName,
        ClassName.get(TextField.class));
    builder.addStatement("$L.setPattern($S)", fieldName, "[0-9]*");
    builder.addStatement("$L.setPreventInvalidInput(true)", fieldName);
    builder.addStatement("$L.setClearButtonVisible(true)", fieldName);
    builder.addStatement("return $L", fieldName);
  }

  @Override
  public TypeName getFieldType() {
    return ClassName.get(String.class);
  }

  @Override
  public ClassName getFormFieldClassName() {
    return ClassName.get(TextField.class);
  }

  @Override
  public ClassName converter() {
    return ClassName.get(StringToIntegerConverter.class);
  }
}
