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

import java.util.Optional;
import javax.tools.Diagnostic.Kind;
import org.rapidpm.vgu.generator.codegenerator.AbstractCodeGenerator;
import org.rapidpm.vgu.generator.model.PropertyModel;
import com.squareup.javapoet.TypeName;

public abstract class AbstractVaadinCodeGenerator extends AbstractCodeGenerator {
  private FieldCreatorFactory fieldCreatorFacktory = new FieldCreatorFactory();

  protected FieldCreator getFieldCreator(PropertyModel propertyModel) {
    Optional<FieldCreator> fieldCreator =
        fieldCreatorFacktory.getFieldCreator(TypeName.get(propertyModel.getType()), FieldType.FORM);
    FieldCreator creator;
    if (!fieldCreator.isPresent()) {
      if (propertyModel.isDataBean()) {
        creator = new DataBeanFieldCreator(propertyModel);
      } else {
        String msg = "No FieldCreator found for type: " + TypeName.get(propertyModel.getType());
        processingEnvironment.getMessager().printMessage(Kind.WARNING, msg,
            propertyModel.getVariableElement().orElse(null));
        creator = new TextFieldCreator();
      }
    } else {
      creator = fieldCreator.get();
    }
    return creator;
  }
}
