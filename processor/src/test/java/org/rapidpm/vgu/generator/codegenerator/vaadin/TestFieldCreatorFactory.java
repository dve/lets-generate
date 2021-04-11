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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

class TestFieldCreatorFactory {
  private FieldCreatorFactory factoryUT = new FieldCreatorFactory();

  @Test
  @DisplayName("Default int creator")
  void test001() {
    Optional<FieldCreator> creator = factoryUT.getFieldCreator(TypeName.INT, FieldType.FORM);
    assertTrue(creator.isPresent());
    assertEquals(IntegerFieldCreator.class, creator.get().getClass());
  }

  @Test
  @DisplayName("Default Integer creator")
  void test002() {
    Optional<FieldCreator> creator =
        factoryUT.getFieldCreator(ClassName.get(Integer.class), FieldType.FORM);
    assertTrue(creator.isPresent());
    assertEquals(IntegerFieldCreator.class, creator.get().getClass());
  }

  @Test
  @DisplayName("Default String creator")
  void test003() {
    Optional<FieldCreator> creator =
        factoryUT.getFieldCreator(ClassName.get(String.class), FieldType.FORM);
    assertTrue(creator.isPresent());
    assertEquals(TextFieldCreator.class, creator.get().getClass());
  }
}
