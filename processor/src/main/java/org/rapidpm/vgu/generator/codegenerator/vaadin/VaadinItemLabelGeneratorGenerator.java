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

import java.io.IOException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import org.rapidpm.vgu.generator.codegenerator.AbstractCodeGenerator;
import org.rapidpm.vgu.generator.codegenerator.JPoetUtils;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.vaadin.flow.component.ItemLabelGenerator;

public class VaadinItemLabelGeneratorGenerator extends AbstractCodeGenerator {

  @Override
  public void writeCode(ProcessingEnvironment processingEnvironment, DataBeanModel model)
      throws IOException {
    setProccesingEnviroment(processingEnvironment);
    TypeSpec comboBoxClass = TypeSpec.classBuilder(model.getName() + classSuffix())
        .addModifiers(Modifier.PUBLIC).addSuperinterface(ParameterizedTypeName
            .get(ClassName.get(ItemLabelGenerator.class), JPoetUtils.getBeanClassName(model)))
        .addMethod(apply(model)).build();
    writeClass(processingEnvironment.getFiler(), model, comboBoxClass);
  }

  private MethodSpec apply(DataBeanModel model) {
    MethodSpec.Builder builder = MethodSpec.methodBuilder("apply")
        .addParameter(JPoetUtils.getBeanClassName(model), "item").addAnnotation(Override.class)
        .addModifiers(Modifier.PUBLIC).returns(TypeName.get(String.class));
    if (model.getCaptionMethod().isPresent()) {
      builder.addStatement("return item." + model.getCaptionMethod().get().getSimpleName() + "()");
    } else {
      builder.addStatement("return item.toString()");
    }
    return builder.build();
  }

  @Override
  public String packageSuffix() {
    return "vaadin";
  }

  @Override
  public String classSuffix() {
    return "ItemLabelGenerator";
  }

}
