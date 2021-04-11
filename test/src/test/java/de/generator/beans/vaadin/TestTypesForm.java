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
package de.generator.beans.vaadin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

class TestTypesForm {
  private TypesFormExtended formUT = new TypesFormExtended();

  @Test
  void testGetIdField() {
    assertThat(formUT.getIdField().isRequired(), is(true));
  }

  @Test
  void testGetIntTypeField() {
    assertThat(formUT.getIntTypeField().isRequired(), is(true));
  }

  @Test
  void testGetIntegerTypeField() {
    assertThat(formUT.getIntegerTypeField().isRequired(), is(false));
  }

  @Test
  void testGetDoubleTypeField() {
    assertThat(formUT.getDoubleTypeField().isRequired(), is(true));
  }

  @Test
  void testGetDoubleClassTypeField() {
    assertThat(formUT.getDoubleClassTypeField().isRequired(), is(false));
  }

  @Test
  void testGetLongTypeField() {
    assertThat(formUT.getLongTypeField().isRequired(), is(true));
  }

  @Test
  void testGetLongClassTypeField() {
    assertThat(formUT.getLongClassTypeField().isRequired(), is(false));
  }

  @Test
  void testGetFloatTypeField() {
    assertThat(formUT.getFloatTypeField().isRequired(), is(true));
  }

  @Test
  void testGetFloatClassTypeField() {
    assertThat(formUT.getFloatClassTypeField().isRequired(), is(false));
  }

  @Test
  void testGetStringTypeField() {
    assertThat(formUT.getStringTypeField().isRequired(), is(false));
  }
}
