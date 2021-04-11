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
package org.rapidpm.vgu.generator.codegenerator;

import static org.rapidpm.vgu.generator.codegenerator.ClassNameUtils.toEnumName;
import java.io.IOException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

public class SortPropertyGenerator extends AbstractCodeGenerator {

  @Override
  public void writeCode(ProcessingEnvironment processingEnvironment, DataBeanModel model)
      throws IOException {
    setProccesingEnviroment(processingEnvironment);
    if (!model.getSortProperties().isEmpty()) {

      Builder enumBuilder = TypeSpec.enumBuilder(enumName(model)).addModifiers(Modifier.PUBLIC);

      model.getSortProperties().forEach(p -> {
        enumBuilder.addEnumConstant(toEnumName(p.getName()));
      });
      if (model.getIdProperty().isPresent()) {
        enumBuilder.addEnumConstant(toEnumName(model.getIdProperty().get().getName()));
      }

      enumBuilder.addMethod(fromPropertyMethod(model));
      TypeSpec typeSpec = enumBuilder.build();


      writeClass(processingEnvironment.getFiler(), model, typeSpec);
    }
  }

  private String enumName(DataBeanModel model) {
    return model.getName() + classSuffix();
  }

  private MethodSpec fromPropertyMethod(DataBeanModel model) {
    MethodSpec.Builder builder =
        MethodSpec.methodBuilder("fromProperty").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(ClassName.get(packageName(model), enumName(model)));
    builder.addParameter(String.class, "property");
    builder.beginControlFlow("switch(property)");
    model.getSortProperties().forEach(sortProperty -> {
      builder.addCode("case $S:\n", sortProperty.getName());
      builder.addStatement("$>return $L$<", toEnumName(sortProperty.getName()));
    });
    builder.addCode("default:\n");
    builder.addStatement("throw new $T( $S + property)", IllegalArgumentException.class,
        "Unknown property: ");
    builder.endControlFlow();
    return builder.build();
  }

  @Override
  public String packageSuffix() {
    return "filter";
  }

  @Override
  public String classSuffix() {
    return "SortFields";
  }

}
