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

import java.io.IOException;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import org.infinitenature.commons.pagination.OffsetRequest;
import org.infinitenature.commons.pagination.Slice;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class QueryInterfaceGenerator extends AbstractCodeGenerator {

  @Override
  public void writeCode(ProcessingEnvironment processingEnvironment, DataBeanModel model)
      throws IOException {
    setProccesingEnviroment(processingEnvironment);
    TypeSpec baseQueriesInterface =
        TypeSpec.interfaceBuilder(model.getName() + classSuffix()).addModifiers(Modifier.PUBLIC)
            .addMethod(findMethod(model)).addMethod(countMethod(model)).build();
    writeClass(processingEnvironment.getFiler(), model, baseQueriesInterface);
  }

  private MethodSpec countMethod(DataBeanModel model) {
    MethodSpec count = MethodSpec.methodBuilder("count")
        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).returns(TypeName.LONG)
        .addParameter(JPoetUtils.getFilterClassName(model), "filter").build();
    return count;
  }

  private MethodSpec findMethod(DataBeanModel model) {
    ClassName sortPropertyClass =
        ClassName.get(ClassNameUtils.getFilterPackage(model), model.getName() + "SortFields");
    TypeName slice = ParameterizedTypeName.get(ClassName.get(Slice.class),
        ClassName.get(model.getPackage(), model.getName()), sortPropertyClass);
    TypeName offsetRequest =
        ParameterizedTypeName.get(ClassName.get(OffsetRequest.class), sortPropertyClass);
    return MethodSpec.methodBuilder("find").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .returns(slice)
        .addParameter(
            ClassName.get(ClassNameUtils.getFilterPackage(model), model.getName() + "Filter"),
            "filter")
        .addParameter(offsetRequest, "offsetRequest").build();
  }

  @Override
  public String packageSuffix() {
    return "repo";
  }

  @Override
  public String classSuffix() {
    return "BaseQueries";
  }

}
