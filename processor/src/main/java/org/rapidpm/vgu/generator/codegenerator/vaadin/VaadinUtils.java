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

import static javax.lang.model.element.Modifier.PUBLIC;
import java.util.function.Consumer;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.rapidpm.vgu.generator.model.DataBeanModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class VaadinUtils {
  private VaadinUtils() {
    throw new IllegalAccessError("Utility class");
  }

  public static TypeSpec i18Wrapper(Consumer<com.squareup.javapoet.MethodSpec.Builder> consumer) {
    com.squareup.javapoet.MethodSpec.Builder localeChangeMethodBuilder =
        MethodSpec.methodBuilder("localeChange").addModifiers(PUBLIC)
            .addParameter(LocaleChangeEvent.class, "event").addAnnotation(Override.class);
    consumer.accept(localeChangeMethodBuilder);

    return TypeSpec.classBuilder("I18NWrapper").superclass(Div.class)
        .addSuperinterface(LocaleChangeObserver.class)
        .addMethod(MethodSpec.constructorBuilder().addParameter(Component.class, "component")
            .addStatement("add(component)").build())
        .addMethod(localeChangeMethodBuilder.build()).build();
  }

  public static ClassName getDataProviderClassName(DataBeanModel model) {
    return ClassName.get(model.getPkg() + ".vaadin", model.getName() + "DataProvider");
  }

  public static ClassName getDataProviderClassName(TypeMirror type) {
    DeclaredType dt = (DeclaredType) type;
    String simpleName = ((QualifiedNameable) dt.asElement()).getSimpleName().toString();
    String fullName = ((QualifiedNameable) dt.asElement()).getQualifiedName().toString();
    String packageName = fullName.substring(0, fullName.length() - simpleName.length() - 1);
    return ClassName.get(packageName + ".vaadin", simpleName + "DataProvider");
  }

  public static ClassName getStringDataProviderClassName(TypeMirror type) {
    DeclaredType dt = (DeclaredType) type;
    String simpleName = ((QualifiedNameable) dt.asElement()).getSimpleName().toString();
    String fullName = ((QualifiedNameable) dt.asElement()).getQualifiedName().toString();
    String packageName = fullName.substring(0, fullName.length() - simpleName.length() - 1);
    return ClassName.get(packageName + ".vaadin", simpleName + "StringFilterDataProvider");
  }
}
