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
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;
import org.infinitenature.commons.pagination.OffsetRequest;
import org.infinitenature.commons.pagination.SortOrder;
import org.infinitenature.commons.pagination.impl.OffsetRequestImpl;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.vaadin.flow.data.provider.SortDirection;

public class StringFilterDataProviderGenerator extends AbstractCodeGenerator {

  private static final String BASE_QUERIES_FIELD = "baseQueries";

  @Override
  public void writeCode(ProcessingEnvironment processingEnvironment, DataBeanModel model)
      throws IOException {
    setProccesingEnviroment(processingEnvironment);
    TypeSpec dataProviderClass =
        TypeSpec.classBuilder(model.getName() + classSuffix()).addModifiers(Modifier.PUBLIC)
            .superclass(ParameterizedTypeName.get(
                ClassName.get("com.vaadin.flow.data.provider", "AbstractBackEndDataProvider"),
                JPoetUtils.getBeanClassName(model), filterClassName(model)))
            .addField(JPoetUtils.getBaseQueriesClassName(model), BASE_QUERIES_FIELD,
                Modifier.PRIVATE)
            .addMethod(constructor(model)).addMethod(fetch(model)).addMethod(size(model))
            .addMethod(createFilter(model)).build();
    writeClass(processingEnvironment.getFiler(), model, dataProviderClass);
  }


  private MethodSpec createFilter(DataBeanModel model) {
    return MethodSpec.methodBuilder("createFilter")
        .addParameter(ParameterSpec.builder(
            ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(String.class)),
            "filterString").build())
        .addModifiers(Modifier.PRIVATE)
        .addStatement("$T filter = new $T()", JPoetUtils.getFilterClassName(model),
            JPoetUtils.getFilterClassName(model))
        .beginControlFlow("if ($N.isPresent() && $N.get().length() > 0)", "filterString",
            "filterString")
        .addStatement("filter." + ClassNameUtils.prefixCamelCase("set",
            model.getDefaultFilterProperty().get().getName()) + "(filterString.get())")
        .endControlFlow().addStatement("return filter")
        .returns(JPoetUtils.getFilterClassName(model)).build();
  }

  private MethodSpec size(DataBeanModel model) {
    return MethodSpec.methodBuilder("sizeInBackEnd").addAnnotation(Override.class)
        .returns(int.class).addModifiers(Modifier.PROTECTED)
        .addParameter(
            ParameterizedTypeName.get(ClassName.get("com.vaadin.flow.data.provider", "Query"),
                JPoetUtils.getBeanClassName(model), filterClassName(model)),
            "query")
        .addStatement("return (int) $N.count(createFilter($N.getFilter()))", BASE_QUERIES_FIELD,
            "query")
        .build();
  }

  protected ClassName filterClassName(DataBeanModel model) {
    return ClassName.get(String.class);
  }

  private MethodSpec fetch(DataBeanModel model) {
    ClassName sortOderClassName = ClassName.get(SortOrder.class);
    ClassName sortDirectionClassName = ClassName.get(SortDirection.class);
    ClassName sortPropertyClassName = JPoetUtils.getSortPropertyClassName(model);
    return MethodSpec.methodBuilder("fetchFromBackEnd").addAnnotation(Override.class)
        .returns(ParameterizedTypeName
            .get(ClassName.get(Stream.class), JPoetUtils.getBeanClassName(model)))
        .addModifiers(Modifier.PROTECTED)
        .addParameter(
            ParameterizedTypeName.get(ClassName.get("com.vaadin.flow.data.provider", "Query"),
                JPoetUtils.getBeanClassName(model), filterClassName(model)),
            "query")
        .addStatement(
            "$T order = $N.getSortOrders().isEmpty() ? $T.ASC : query.getSortOrders().get(0).getDirection() == $T.ASCENDING ? $T.ASC : $T.DESC",
            sortOderClassName, "query", sortOderClassName, sortDirectionClassName,
            sortOderClassName, sortOderClassName)
        .addStatement(
            "$T property = query.getSortOrders().isEmpty() ?  $T.$N : $T.valueOf(query.getSortOrders().get(0).getSorted())",
            sortPropertyClassName, sortPropertyClassName,
            ClassNameUtils.toEnumName(model.getDefaultSortProperty().get().getName()),
            sortPropertyClassName)
        .addStatement(
            "$T offsetRequest = new $T(query.getOffset(), query.getLimit(), order, property)",
            ClassName.get(OffsetRequest.class), ClassName.get(OffsetRequestImpl.class))
        .addStatement(
            "return baseQueries.find(createFilter(query.getFilter()), offsetRequest).getContent().stream()")
        .build();
  }

  private MethodSpec constructor(DataBeanModel model) {
    return MethodSpec.constructorBuilder()
        .addParameter(ParameterSpec
            .builder(JPoetUtils.getBaseQueriesClassName(model), BASE_QUERIES_FIELD).build())
        .addModifiers(Modifier.PUBLIC).addStatement("super()")
        .addStatement("this.baseQueries = baseQueries").build();
  }

  @Override
  public String packageSuffix() {
    return "vaadin";
  }

  @Override
  public String classSuffix() {
    return "StringFilterDataProvider";
  }

}
