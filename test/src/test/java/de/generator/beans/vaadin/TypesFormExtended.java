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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

public class TypesFormExtended extends TypesForm {

  public Button getSubmitButton() {
    return submitButton;
  }

  public Button getCancelButton() {
    return cancelButton;
  }

  public Button getResetButton() {
    return resetButton;
  }

  public Component getLayout() {
    return layout;
  }

  public TextField getIdField() {
    return idField;
  }

  public TextField getIntTypeField() {
    return intTypeField;
  }

  public TextField getIntegerTypeField() {
    return integerTypeField;
  }

  public TextField getDoubleTypeField() {
    return doubleTypeField;
  }

  public TextField getDoubleClassTypeField() {
    return doubleClassTypeField;
  }

  public TextField getLongTypeField() {
    return longTypeField;
  }

  public TextField getLongClassTypeField() {
    return longClassTypeField;
  }

  public TextField getFloatTypeField() {
    return floatTypeField;
  }

  public TextField getFloatClassTypeField() {
    return floatClassTypeField;
  }

  public TextField getStringTypeField() {
    return stringTypeField;
  }

}
