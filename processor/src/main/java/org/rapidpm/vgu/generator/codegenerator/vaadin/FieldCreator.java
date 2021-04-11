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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public interface FieldCreator {
  public boolean isResponsibleFor(TypeName typeName);

  public int getPriority(FieldType fieldType);

  public void createAndReturnFormField(MethodSpec.Builder builder);

  public TypeName getFormFieldClassName();

  public TypeName getFieldType();

  public default void createAndReturnFilterField(MethodSpec.Builder builder) {
    createAndReturnFormField(builder);
  }

  public default TypeName getFilterFieldClassName() {
    return getFormFieldClassName();
  }

  public default ClassName converter() {
    return null;
  }

  public default boolean allowsRequiered() {
    return true;
  }

  public default boolean allowsEmpty() {
    return false;
  }
}
