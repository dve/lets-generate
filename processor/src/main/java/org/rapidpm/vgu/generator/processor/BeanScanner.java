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
package org.rapidpm.vgu.generator.processor;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementScanner8;
import org.rapidpm.vgu.generator.model.DataBeanModel;

public class BeanScanner extends ElementScanner8<Void, DataBeanModel> {
  private ProcessingEnvironment processingEnvironment;
  private Set<String> importedTypes = new HashSet<>();

  public void setProcessingEnvironment(ProcessingEnvironment processingEnvironment) {
    this.processingEnvironment = processingEnvironment;
  }

  public Set<String> getImportedTypes() {
    return importedTypes;
  }

  @Override
  public Void visitVariable(VariableElement e, DataBeanModel p) {
    if (e.asType().getKind() == TypeKind.DECLARED) {
      importedTypes.add(e.asType().toString());
    }

    if (e.getKind() == ElementKind.FIELD) {
      FilterPropertyGem displayPropertyGem = FilterPropertyGem.instanceOn(e);
      if (displayPropertyGem != null) {
        String propertyClassName;
        if (e.asType().getKind().isPrimitive()) {
          PrimitiveType pt =
              processingEnvironment.getTypeUtils().getPrimitiveType(e.asType().getKind());
          propertyClassName = pt.toString();
        } else {
          propertyClassName =
              processingEnvironment.getTypeUtils().asElement(e.asType()).getSimpleName().toString();
        }
        String propertyName = e.getSimpleName().toString();
      }
    }
    return super.visitVariable(e, p);
  }

  private void createPropertyDescriptions(DataBeanModel p, FilterPropertyGem displayPropertyGem,
      String propertyClassName, String propertyName) {
    System.out.println("bla");
  }

}
